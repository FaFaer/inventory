package Tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ioTool {
	public final static String driver = "com.mysql.jdbc.Driver";
	public final static String user = "root";
	public final static String password = "199511";
	public final static String url = "jdbc:mysql://localhost:3306/inventory";
	public static Connection connection = null;
	public static ResultSet re = null;
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 主函数 调用创建文件函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			createfile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 数据库查询表的名字放到list中
	 * 
	 * @return
	 */
	static List<String> Name() {

		java.util.List<String> name = new ArrayList<String>();
		try {
			connection = DriverManager.getConnection(url, user, password);
			String sql = "show tables";
			PreparedStatement pre = connection.prepareStatement(sql);
			re = pre.executeQuery();
			while (re.next()) {
				name.add(re.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * 创建文件函数 用for循环语句把list里的表名字拿出来创建文件，用sql语句根据表名字查询表结构，用输出流完成对实体类的拼接。
	 * 
	 * @throws IOException
	 */
	static void createfile() throws IOException {
		List<String> name = Name();
		for (int i = 0; i < name.size(); i++) {
			String a = name.get(i);
			a = a.substring(0, 1).toUpperCase() + a.substring(1);
			String b = "F:\\fieldwork\\java\\text\\Inventory\\src\\mode\\" + a + ".java";
			File file = new File(b);
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			FileOutputStream outputStream = new FileOutputStream(file);
			StringBuffer bufferhead =new StringBuffer( " package mode;" + "\t\n" );
			StringBuffer buffermid =new StringBuffer( "public class" + "   " + a + "{");
			StringBuffer bufferfoot =new StringBuffer("");
			String cotype = "";
			String coname = "";
			String sql = "select * from " + "" + a;
			try {
				PreparedStatement pre = connection.prepareStatement(sql);
				re = pre.executeQuery();
				ResultSetMetaData da = re.getMetaData();
				for (int k = 0; k < da.getColumnCount(); k++) {
					cotype = da.getColumnTypeName(k + 1);
					coname = da.getColumnName(k + 1);
					if (cotype.equalsIgnoreCase("INT")||cotype.equalsIgnoreCase("DOUBLE")) {
						cotype = cotype.toLowerCase();
					}
					if (cotype == "VARCHAR" || "VARCHAR".equals(cotype)) {
						cotype = "String";
					}
					if (cotype == "DATETIME" || "DATETIME".equals(cotype)) {
						cotype = "Date";
						bufferhead.append("import java.util.Date;");
					}
					if (cotype == "text" || "text".equals(cotype)) {
						cotype = "char";
					}
				  buffermid.append("\t\n" + "private" + " " + cotype+"  "+coname+";");
				String h = coname.substring(0, 1).toUpperCase() + coname.substring(1);
				  bufferfoot.append("\n\tpublic " + cotype + "  get" + h + "() {" + "\t\n"+"  return "+ coname + ";\n\t}");
				  bufferfoot.append("\n\tpublic void "  + "  set" + h + "(" + cotype + " " +coname + ") {" +"\n\t"+ "this." + coname + "=" + coname + ";\n\t}");
	 
				}
	              String fun = "\t\n"+a+"() {\r\n" + "\r\n" + "}";
					outputStream.write(bufferhead.toString().getBytes());
					outputStream.write(buffermid.toString().getBytes());
					outputStream.write(fun.getBytes());
					outputStream.write(bufferfoot.toString().getBytes());
				String end = "\t\n" + "}";
				outputStream.write(end.getBytes());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
