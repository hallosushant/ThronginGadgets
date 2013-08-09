package com.sushant.verma.common.dto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ModelCreator{
	private static Logger log=LogManager.getLogger(ModelCreator.class);
	private static String informationSchema="information_schema";
	private static String tableSchema="throngindb";
	private static String packageString="package com.sushant.verma.common.dto;";
	private static String importStatement="import java.sql.Timestamp;";//\nimport java.util.Date;";
	private static String dalFolderPath="\\src\\com\\sushant\\verma\\common\\dto";
	
	public static void main (String [] args) throws Exception{

		String[] tableNames = null;
		String table_name;
		String count = null;
		
		
		/**
		 * connects to information_schema and obtains the no of columns in the given tableSchema
		 */
		Connection conn=getDbConnect(informationSchema);
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select count(table_name) as count from tables  where table_schema='"+tableSchema+"'");
		
		Statement st = conn.createStatement();
		st = conn.createStatement();
	    ResultSet rs = st.executeQuery(sql.toString());
		while(rs.next()){
			count=rs.getString("count");
			log.debug("Total Tables in "+tableSchema+"="+count);
			tableNames=new String[Integer.parseInt(count)];
		}

		
		
		
		
		
		
		
		
		
		/**
		 * Depending upon the total count of tables a String Array tableNames[] is created to store
		 * all the tables in the given tableSchema
		 */
		StringBuilder sql1=new StringBuilder();
		sql1.append(" select table_name from tables  where table_schema='"+tableSchema+"'");
		
		Statement st1 = conn.createStatement();

	    ResultSet rs1 = st1.executeQuery(sql1.toString());
	    int k=0;
		while(rs1.next()){
			table_name=rs1.getString("table_name");
			tableNames[k]=table_name;
			k++;
		}

		/**
		 * close the current connection to information_schema so as to connect to tableSchema
		 */
		conn.close();
		
		
		
		
		
		
		/**
		 * Connects to tableSchema 
		 */
		
		conn=getDbConnect(tableSchema);

		/**
		 * creates a loop for all the tables in the tableSchema with the count obtained
		 */
		for(int j=0;j<Integer.parseInt(count);j++)
		{
			log.info("******************************* "+j+" *********************************************");
			File myJavaFile = null;
			String fileContent=null;
			StringBuffer variableBuffer=new StringBuffer();
			StringBuffer getterBuffer=new StringBuffer();
			StringBuffer setterBuffer=new StringBuffer();

			/**
			 * obtain the resultSet and resultSetMetadata for the current Table
			 */
			StringBuilder sql2=new StringBuilder();
			sql2.append(" select * from "+tableNames[j]);
			Statement st2 = conn.createStatement();
			st2 = conn.createStatement();
		    ResultSet rs2 = st2.executeQuery(sql2.toString());		
			
			ResultSetMetaData rsMetaData = rs2.getMetaData();		

			

			log.debug("Table Name=="+tableNames[j]);
	    
		    
		    
		    /**
		     * Creates the class Name String to be used in the java file created for the current Table Model Class
		     */
		    String tableName=tableNames[j].toUpperCase();

		    String[] classNameUnit=tableName.replaceAll("_", ":").split(":");
		    StringBuffer classNamePart=new StringBuffer();
			      for(int l=0;l<classNameUnit.length;l++){
			    		  classNamePart.append(classNameUnit[l].substring(0, 1)+classNameUnit[l].toLowerCase().substring(1));
			      }
			String className="public class "+classNamePart+"Dto{";
//			log.debug(className);
		    
		    
		    
		    
		    /**
		     * Creates the variable Name String from the column Name 
		     * and append it to variableBuffer to be used in the java file created for the current Table Model Class
		     */
			int numberOfColumns = rsMetaData.getColumnCount();
			log.debug("No of Columns=="+numberOfColumns);
		    for (int i = 1; i <= numberOfColumns; i++) {
		      String columnName=rsMetaData.getColumnName(i);
		      String[] columnNameUnit=columnName.replaceAll("_", ":").split(":");
		      StringBuffer columnNamePart=new StringBuffer();
			      for(int l=0;l<columnNameUnit.length;l++){
			    	  if(l==0)
			    		  columnNamePart.append(columnNameUnit[l].toLowerCase());
			    	  else 
			    		  columnNamePart.append(columnNameUnit[l].substring(0, 1)+columnNameUnit[l].toLowerCase().substring(1));
			      }

		      String type=rsMetaData.getColumnClassName(i).replaceAll("java.lang.", "");
		      log.debug("======type======="+type);
		      String type1=type.replaceAll("java.sql.", "");
		      String type2=type1.replaceAll("java.math.","");
		      String type3=type2.replaceAll("BigDecimal", "int");
		      
		      String variable="\n\tprivate "+type3+" "+columnNamePart+";\n";
		      variableBuffer.append(variable);
//		      log.debug(variable);
		      
		      
		      
		      
		      
		      
		      
			    /**
			     * Creates the getters and setters for variable Name 
			     * and append it to getter/setterBuffer to be used in the java file created for the current Table Model Class
			     */
		      StringBuffer columnNameVar=new StringBuffer();
		      for(int l=0;l<columnNameUnit.length;l++){
		    	  columnNameVar.append(columnNameUnit[l].substring(0, 1)+columnNameUnit[l].toLowerCase().substring(1));
		      }
		      String getter="\n\tpublic "+type3+" get"+columnNameVar+"(){\n\t return "+columnNamePart+";\n\t}";
//		      log.debug(getter);
		      getterBuffer.append(getter);
		      
		      String setter="\n\tpublic void set"+columnNameVar+"("+type3+" "+columnNamePart+"){\n\t this."+columnNamePart+" = "+columnNamePart+";\n\t}";
//		      log.debug(setter);
		      setterBuffer.append(setter);

		      
		      
		      /**
		       * creates the import for TIMESTAMP and Date
		       */
//		      if(type3.equals("Timestamp")|| type3.equals("Date"))
		      	fileContent=packageString+"\n\n"+importStatement+"\n\n"+className+"\n\n\t"+variableBuffer+"\n\t"+getterBuffer+"\n\t"+setterBuffer+"\n}";
//		      else
//		    	fileContent=packageString+"\n\n"+className+"\n\n\t"+variableBuffer+"\n\t"+getterBuffer+"\n\t"+setterBuffer+"\n}";
		      
		      
		      
		      
		      File currentDir = new File("");
		      String rootFilePath = currentDir.getAbsolutePath();
		      
		      String folderName=rootFilePath+dalFolderPath;
		      myJavaFile = createFile(new File(folderName), classNamePart+ "Dto.java");
	      
		    }
		      log.debug(myJavaFile.getAbsolutePath()+"\n\n"+fileContent);
		      Writer writer=new OutputStreamWriter(new FileOutputStream(myJavaFile.getAbsolutePath()));
		      writer.write(fileContent);
		      writer.close();
		    
	
		}
	}
	
	/**
	 * Creates a File with FILE_NAME as "fileName" in Directory "destDir" if
	 * exists already else creates it.
	 * 
	 * @param destDir
	 * @param filename
	 * @return file
	 */
	public static File createFile(File destDir, String fileName) {
		File file = new File(destDir, fileName);
		File parent = file.getParentFile();
		if (parent != null)
			parent.mkdirs();
		return file;
	}

	public static Connection getDbConnect(String schemaName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
		Connection conn = null;
        String userName = "root";
        String password = "admin";
        String url = "jdbc:mysql://localhost/"+schemaName;
        Class.forName ("com.mysql.jdbc.Driver").newInstance ();
        conn = DriverManager.getConnection (url, userName, password);
        if (conn!=null){
        	log.debug("Database connection established");
            return conn;
        }
        else 
        {
        	log.debug("Cannot connect to database server");
        	throw new RuntimeException();
        }
        
    }


}
