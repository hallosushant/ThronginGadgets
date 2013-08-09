package com.sushant.verma.common.constants;

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

public class DbConstantsCreator{
	private static Logger log=LogManager.getLogger(DbConstantsCreator.class);
	private static final String informationSchema="information_schema";
	private static final String tableSchema="throngindb";
	private static final String packageString="package com.sushant.verma.common.constants;";
	private static final String dbConstantsFolderPath="\\src\\com\\sushant\\verma\\common\\constants";
	private static final String dbConstantsClassName="DbConstants";
	private static final String fullClassName="public class DbConstants {";
	private static final String constantsModifier="public static final String ";
	
	public static void main (String [] args) throws Exception{

		HashMap<String,Object> resultMap=getAllTableNames();
		String[] tableNames=(String[]) resultMap.get("tableNames");
		String count=(String) resultMap.get("count");

		/**
		 * Connects to tableSchema 
		 */
		
		Connection conn=getDbConnect(tableSchema);
		StringBuffer constantBuffer=new StringBuffer();
	    String  fileStart=packageString+"\n\n"+fullClassName;


		/**
		 * creates a loop for all the tables in the tableSchema with the count obtained
		 */
		for(int j=0;j<Integer.parseInt(count);j++)
		{
			log.info("******************************* "+j+" *********************************************");
			
			StringBuffer constantsBuffer=new StringBuffer();
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
		     * Creates the tableConstant Name String
		     */
		    String tableName=tableNames[j].toUpperCase();
/*		    String[] classNameUnit=tableName.replaceAll("_", ":").split(":");
		    StringBuffer classNamePart=new StringBuffer();
			      for(int l=0;l<classNameUnit.length;l++){
			    	  if(l==0)
			    		  classNamePart.append(classNameUnit[l].toLowerCase());
			    	  else 
			    		  classNamePart.append(classNameUnit[l].substring(0, 1)+classNameUnit[l].toLowerCase().substring(1));
			      }
			String constantName=classNamePart.toString();
*/			
		    String tableConstantFullName="\n\t"+constantsModifier+tableName+" = \""+tableName+"\";";
			log.debug(tableConstantFullName);
			constantsBuffer.append(tableConstantFullName);
		    
		    
		    
		    /**
		     * Creates the Constant Name String from the column Name 
		     * and append it to constantsBuffer to be used in the java file created 
		     */
			int numberOfColumns = rsMetaData.getColumnCount();
			log.debug("No of Columns=="+numberOfColumns);
		    for (int i = 1; i <= numberOfColumns; i++) {
		      String columnName=rsMetaData.getColumnName(i);
		      
/*		      String[] columnNameUnit=columnName.replaceAll("_", ":").split(":");
		      StringBuffer columnNamePart=new StringBuffer();
			      for(int l=0;l<columnNameUnit.length;l++){
			    	  if(l==0)
			    		  columnNamePart.append(columnNameUnit[l].toLowerCase());
			    	  else 
			    		  columnNamePart.append(columnNameUnit[l].substring(0, 1)+columnNameUnit[l].toLowerCase().substring(1));
			      }

			  constantName=columnNamePart.toString();
*/			  String columnConstantFullName="\n\t"+constantsModifier+columnName+" = \""+columnName+"\";";
			  constantsBuffer.append(columnConstantFullName);
		      }
		    
		    
		    constantBuffer.append(constantsBuffer);
		}
			
			String[] constantStrings=constantBuffer.toString().split(";");
			String[] uniqueConstantStrings = getUniqueElements(constantStrings);
			
			constantBuffer=new StringBuffer();
			for(int i=0;i<uniqueConstantStrings.length;i++){
				constantBuffer.append(uniqueConstantStrings[i]+";");
			}
			
			StringBuffer fileContentBuffer=new StringBuffer();
		    fileContentBuffer.append(fileStart);
		    fileContentBuffer.append(constantBuffer);
			fileContentBuffer.append("\n}");
			String fullFileContent=fileContentBuffer.toString();
			
			createFile(fullFileContent);
	}


	private static String[] getUniqueElements(String[] allStrs) {
        String[] temp = new String[allStrs.length]; // null elements
        int count = 0;
        for(int j = 0; j < allStrs.length; j++) {
            if(isUnique(allStrs[j], temp))
                temp[count++] = allStrs[j];
        }
        String[] uniqueStrs = new String[count];
        System.arraycopy(temp, 0, uniqueStrs, 0, count);
        return uniqueStrs;
    }
 
    private static boolean isUnique(String s, String[] array) {
        for(int j = 0; j < array.length; j++) {
            if(array[j] != null && s.equals(array[j]))
                return false;
        }
        return true;
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
	      
	      String folderName=rootFilePath+dbConstantsFolderPath;
	      File myJavaFile = createFile(new File(folderName), dbConstantsClassName+".java");
	      
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
