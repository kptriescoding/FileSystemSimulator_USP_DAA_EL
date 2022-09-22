package Database;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import Models.*;
import Models.File;

import javax.net.ssl.SSLException;

public class SqlCommands {
    private final Connection conn;
    public SqlCommands(){
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/",
                    "kptries", "password");
            Statement st = con.createStatement();

            st.executeUpdate("CREATE DATABASE IF NOT EXISTS FileSystem");
            this.conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/FileSystem",
                    "kptries", "password");
            Statement stmt=this.conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS INodeTable (INode INT,File VARBINARY(8000))");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int storeObject(Object ob,int inodeNumber){
        byte[] data;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(ob);
            oos.flush();
            oos.close();
            baos.close();
            data = baos.toByteArray();
            Statement st = conn.createStatement();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO INodeTable(INode,File) VALUES (?,?)");
            stmt.setInt(1, inodeNumber);
            stmt.setObject(2,data);
            stmt.executeUpdate();
            return inodeNumber;
        }
        catch (IOException | SQLException e) {
            System.out.println("" + e);
        }
        return -1;
    }
    public void UpdateObject(Object ob,int INodeNumber){
        byte[] data;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(ob);
            oos.flush();
            oos.close();
            baos.close();
            data = baos.toByteArray();
            Statement st = conn.createStatement();
            PreparedStatement stmt = conn.prepareStatement("UPDATE INodeTable SET File=? WHERE INode="+INodeNumber);
            stmt.setObject(1, data);
            stmt.executeUpdate();
        }
        catch (IOException | SQLException e) {
            System.out.println("" + e);
        }
    }
    public  Object retrieveObject(int InodeNumber){
        try{
            byte[] data;

            PreparedStatement stmt = conn.prepareStatement("SELECT * from INodeTable where INode="+InodeNumber);
                    ResultSet rs = stmt.executeQuery();
            rs.next();
            int INodeNumber=rs.getInt("INode");
            data=rs.getBytes("File");
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream is = new ObjectInputStream(in);
            return is.readObject();
        }
        catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println("" + e);
        }
        return null;
    }
    public void removeObject(int InodeNumber){
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM INodeTable WHERE INode=" + InodeNumber);
        }
        catch (SQLException e){
            System.out.println(""+e);
        }
    }
//    public static void main(String[] args) {
//        File f=new File();
////        f.parentINodeNumber=0;
////        f.contents="a cras semper. Et molestie ac a in fermentum et sollicitudin ac. Tempor id eu nisl nunc mi ipsum faucibus.";
//        Directory d=new Directory();
//        d.parentINodeNumber=0;
//        DirContents contents=new DirContents();
//        SqlCommands sql = new SqlCommands();
////        contents.inodeNumber=sql.storeObject(f);
//        contents.name="Dummy File";
////        File new_f = (File) sql.retrieveObject(contents.inodeNumber);
//        d.contents=new ArrayList<>();
//        d.contents.add(contents);
////        int dir_no=sql.storeObject(d);
//        sql.UpdateObject(d,1);
//        Object dir=  sql.retrieveObject(1);
//        System.out.println(dir);
////        System.out.println(dir.parentINodeNumber);
////        System.out.println(dir.contents.get(0).name);
////        System.out.println(new_f.contents);
//    }
}
