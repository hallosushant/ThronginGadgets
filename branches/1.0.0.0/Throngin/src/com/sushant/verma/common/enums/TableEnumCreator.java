package com.sushant.verma.common.enums;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TableEnumCreator{
	private static Logger log=LogManager.getLogger(TableEnumCreator.class);
	private static final String informationSchema="information_schema";
	private static final String tableSchema="throngindb";
	private static final String packageString="package com.sushant.verma.common.enums;";
	private static final String enumFolderPath="\\src\\com\\sushant\\verma\\common\\enums";
	private static final String enumClassName="TableEnums";
	private static final String fullClassName="public class TableEnums {";
	private static final String enumModifier="public enum ";
	
	public static void main (String [] args) throws Exception{

		HashMap<String,Object> resultMap=getAllTableNames();
		String[] tableNames=(String[]) resultMap.get("tableNames");
		String count=(String) resultMap.get("count");

		/**
		 * Connects to tableSchema 
		 */
		
		Connection conn=getDbConnect(tableSchema);
		StringBuffer fileContentBuffer=new StringBuffer();
	    String  fileStart=packageString+"\n\n"+fullClassName;
	    fileContentBuffer.append(fileStart);
	    StringBuffer tableNameBuffer=new StringBuffer();
	    
		/**
		 * creates a loop for all the tables in the tableSchema with the count obtained
		 */
		for(int j=0;j<Integer.parseInt(count);j++)
		{
			log.info("******************************* "+j+" *********************************************");
			
			StringBuffer enumVariableBuffer=new StringBuffer();
			StringBuffer getterBuffer=new StringBuffer();
			StringBuffer enumConstructorBuffer=new StringBuffer();
			

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
		     * Creates the enum Name String
		     */
		    String tableName=tableNames[j].toUpperCase();

		    
		    String enumVar="\n\t\t"+tableName+",";
		    tableNameBuffer.append(enumVar);
		    
		    String[] enumNameUnit=tableName.replaceAll("_", ":").split(":");
		    StringBuffer enumNamePart=new StringBuffer();
			      for(int l=0;l<enumNameUnit.length;l++){
			    	  enumNamePart.append(enumNameUnit[l].substring(0, 1)+enumNameUnit[l].toLowerCase().substring(1));
			      }
			String enumName=enumNamePart+"Enum ";
			String enumVariable=enumNamePart+"Enum "+enumNamePart.substring(0, 1).toLowerCase()+enumNamePart.substring(1)+";";
			String enumFullName="\n\t"+enumModifier+enumNamePart+"Enum\n\t{";
			log.debug(enumVariable+enumFullName);
		    
		    
		    
		    
		    /**
		     * Creates the variable Name String from the column Name 
		     * and append it to enumVariableBuffer to be used in the java file created 
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

			  int columnSize=rsMetaData.getColumnDisplaySize(i);
			  
		      String type=rsMetaData.getColumnClassName(i).replaceAll("java.lang.", "");
		      String type1=type.replaceAll("java.sql.", "");
		      String type2=type1.replaceAll("java.math.","");
		      String type3=type2.replaceAll("BigDecimal", "int");
		      
		      String enumColumnName="\n\t\t"+columnName+",";
		      String enumVaraibleName=columnNamePart+"("+columnSize+"),";
		      String enumVaraibleType=columnNamePart+"_Type(\""+type3+"\"),";
		      enumVariableBuffer.append(enumColumnName);
		      enumVariableBuffer.append(enumVaraibleName);
		      enumVariableBuffer.append(enumVaraibleType);
		      }
		    
		      String constructorArgument1="columnLength";
		      String constructorArgument1Type="int";
		      String constructorArgument2="columnType";
		      String constructorArgument2Type="String";
		      String enumVar1="\n\t\tprivate "+constructorArgument1Type+" "+constructorArgument1+";";
		      String enumVar2="\n\t\tprivate "+constructorArgument2Type+" "+constructorArgument2+";";

		      enumConstructorBuffer.append("\n\t\t"+enumName+"(){}");
		      enumConstructorBuffer.append("\n\t\t"+enumName+"("+constructorArgument1Type+" "+constructorArgument1+")\n\t\t{");
		      enumConstructorBuffer.append("\n\t\t\tthis."+constructorArgument1+"="+constructorArgument1+";\n\t\t}");
		      enumConstructorBuffer.append("\n\t\t"+enumName+"("+constructorArgument2Type+" "+constructorArgument2+")\n\t\t{");
		      enumConstructorBuffer.append("\n\t\t\tthis."+constructorArgument2+"="+constructorArgument2+";\n\t\t}");

		      getterBuffer.append("\n\t\tpublic "+constructorArgument1Type+" get"+constructorArgument1.substring(0,1).toUpperCase()+constructorArgument1.substring(1)+"()\n\t\t{");
		      getterBuffer.append("\n\t\t\treturn "+constructorArgument1+";\n\t\t}");
		      getterBuffer.append("\n\t\tpublic "+constructorArgument2Type+" get"+constructorArgument2.substring(0,1).toUpperCase()+constructorArgument2.substring(1)+"()\n\t\t{");
		      getterBuffer.append("\n\t\t\treturn "+constructorArgument2+";\n\t\t}");
		      getterBuffer.append("\n\t}");
		      
		      
		      fileContentBuffer.append("\n\n\t"+enumVariable+enumFullName+enumVariableBuffer+";\n\t"+enumVar1+enumVar2+enumConstructorBuffer+getterBuffer);
		}
		    String tableEnumFullName="\n\n\t"+enumModifier+ " TableEnum\n\t{";
		    fileContentBuffer.append(tableEnumFullName+tableNameBuffer+";\n\t\tTableEnum(){}\n\t}");

			fileContentBuffer.append("\n}");
			String fullFileContent=fileContentBuffer.toString().replaceAll(",;", ";");
			
			
			createFile(fullFileContent);

	}


	
	/**
	 * create the file with the given fileContent and at the relative file location specified by enumFolderPath
	 * and fileName as enumClassName.java;  
	 * @param fullFileContent
	 * @throws IOException
	 */
	private static void createFile(String fullFileContent) throws IOException {
	      File currentDir = new File("");
	      String rootFilePath = currentDir.getAbsolutePath();
	      
	      String folderName=rootFilePath+enumFolderPath;
	      File myJavaFile = createFile(new File(folderName), enumClassName+".java");
	      
	      log.debug(myJavaFile.getAbsolutePath()+"\n\n"+fullFileContent);
	      Writer writer=new OutputStreamWriter(new FileOutputStream(myJavaFile.getAbsolutePath()));
	      writer.write(fullFileContent);
	      writer.close();
	}




	/**
	 * connects to information_schema and obtains the no of Tables in the given tableSchema
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private static HashMap<String,Object> getAllTableNames() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		String[] tableNames = null;
		String count = null;
		String table_name;
		
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
		st1 = conn.createStatement();
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
		
		HashMap<String,Object> returnMap=new HashMap<String,Object> ();
		returnMap.put("tableNames",tableNames);
		returnMap.put("count",count);
		return returnMap;

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
