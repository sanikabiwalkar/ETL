/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbms_miniproject;

import java.sql.*;
import static dbms_miniproject.DatafromText.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import jxl.Workbook;
import jxl.Sheet;
import jxl.Cell;
import jxl.read.biff.BiffException;
import jxl.*;

/**
 *
 * @author sanikabiwalkar
 */
public class DBMS_MINIPROJECT {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Integer> id = new ArrayList<>();
    static ArrayList<String> fname = new ArrayList<>();
    static ArrayList<String> lname = new ArrayList<>();
    static ArrayList<String> addr = new ArrayList<>();
    static ArrayList<String> gender = new ArrayList<>();
    static ArrayList<Long> salary = new ArrayList<>();

    public static void print() {
        for (int i = 0; i < id.size(); i++) {
            System.out.print(id.get(i) + "    ");
            System.out.print(fname.get(i) + "  ");
            System.out.print(lname.get(i) + "  ");
            System.out.print(salary.get(i) + "   ");
            System.out.print(addr.get(i) + "   ");
            System.out.print(gender.get(i) + "   ");
            System.out.println();
        }
    }

    public static void extractfromsql() throws Exception {

        try {
            //   Class.forName("com.mysql.jdbc.Driver"); // throws ClassNotFoundException
            // Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
            //ystem.out.println("Connected");
            //java.sql.Statement st = conn.createStatement();
            Class.forName("com.mysql.jdbc.Driver"); // throws ClassNotFoundException
            Connection connn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tel", "root", "");
            System.out.println("Connected");
            java.sql.Statement stm = connn.createStatement();
            String sql = "select * from emp";
            PreparedStatement psmt = connn.prepareStatement(sql);
            ResultSet rst = psmt.executeQuery();
            while (rst.next()) {
                DatafromSql.ID.add(Integer.parseInt(rst.getString("ID")));
                DatafromSql.Fname.add(rst.getString("Fname"));
                DatafromSql.Lname.add(rst.getString("Lname"));
                DatafromSql.Sal.add(Long.parseLong(rst.getString("Salary")));
                DatafromSql.Address.add(rst.getString("Address"));
                DatafromSql.Gen.add(rst.getString("Gender"));
            }

        } catch (SQLException e) {

        }

    }

    public static void extractfromExcel() throws IOException, BiffException {
        File f = new File("/Users/sanikabiwalkar/Desktop/miniproject/Book1.xls");
        Workbook wb = Workbook.getWorkbook(f);
        Sheet s = wb.getSheet(0);
        int row = s.getRows();
        int col = s.getColumns();
        for (int i = 1; i < row; i++) {
            int j = 0;
            Cell c = s.getCell(j, i);
            DatafromExcel.ID.add(Integer.parseInt(c.getContents()));
            j++;
            c = s.getCell(j, i);
            DatafromExcel.Fname.add(c.getContents());
            j++;
            c = s.getCell(j, i);
            DatafromExcel.Lname.add(c.getContents());
            j++;
            c = s.getCell(j, i);
            DatafromExcel.Sal.add(Long.parseLong(c.getContents()));
            j++;
            c = s.getCell(j, i);
            DatafromExcel.Address.add(c.getContents());
            j++;
            c = s.getCell(j, i);
            DatafromExcel.Gen.add(c.getContents());
            j++;

        }
    }

    public static void load() throws IOException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver"); // throws ClassNotFoundException
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tel", "root", "");
        System.out.println("Connected");
        java.sql.Statement stm = conn.createStatement();
        //PreparedStatement psmt1 = conn.prepareStatement(sql1);
        for (int i = 0; i < id.size(); i++) {
            stm.executeUpdate("insert into finalEmp(ID,fname,lname,salary,address,gender) values('" + id.get(i) + "', '" + fname.get(i) + "', '" + lname.get(i) + "', '" + salary.get(i) + "', '" + addr.get(i) + "', '" + gender.get(i) + "')");

        }

    }

    public static void transform() {

        for (int i = 0; i < 10; i++) {
            String s = DatafromText.name.get(i);
            String name[] = s.split(" ");
            DatafromText.fname.add(name[0]);
            DatafromText.lname.add(name[1]);
            String g = DatafromExcel.Gen.get(i);
            if (g.equals("M")) {
                gender.add("Male");
            } else {
                if (gender.add("Female")) {
               gender.add("Female");

                }
            }
            id.add(DatafromText.no.get(i));
            fname.add(DatafromText.fname.get(i));
            lname.add(DatafromText.lname.get(i));
            addr.add(DatafromText.address.get(i));
            salary.add(DatafromText.sal.get(i));

        }

        for (int i = 0; i < 10; i++) {
            String g = DatafromExcel.Gen.get(i);
            if (g.equals("M")) {
               gender.add("Male");
            } else {
                if (g.equals("F")) {
               gender.add("Female");
                }
            }
            id.add(DatafromExcel.ID.get(i));
            fname.add(DatafromExcel.Fname.get(i));
            lname.add(DatafromExcel.Lname.get(i));
            addr.add(DatafromExcel.Address.get(i));
            salary.add(DatafromExcel.Sal.get(i));

        }
        for (int i = 0; i < 10; i++) {
            String g = DatafromSql.Gen.get(i);
            if (g.equals("M")) {
                gender.add("Male");

            } else {
                if (g.equals("F")) {
               gender.add("Female");
                }
            }
            id.add(DatafromSql.ID.get(i));
            fname.add(DatafromSql.Fname.get(i));
            lname.add(DatafromSql.Lname.get(i));
            addr.add(DatafromSql.Address.get(i));
            salary.add(DatafromSql.Sal.get(i));
        }

    }

    public static void extractfromtext() {
        try {
            //System.out.println("HEllo ");
            //System.out.println("HEllp");
            FileInputStream f = new FileInputStream("/Users/sanikabiwalkar/Desktop/miniproject/EmpData.txt");
            //System.out.println("HEllo ");
            BufferedReader br = new BufferedReader(new InputStreamReader(f));
            String line, z;
            z = br.readLine();
            while ((line = br.readLine()) != null) {
                String t[];
                t = line.split(",");
                //   System.out.println(Arrays.toString(t));

                int no = Integer.parseInt(t[0]);
                DatafromText.no.add(no);
                z = t[1];
                DatafromText.name.add(t[1]);
                DatafromText.sal.add(Long.parseLong(t[2]));
                DatafromText.address.add(t[3]);
                DatafromText.gen.add(t[4]);

            }
        } catch (IOException e) {

        }
    }
}

class DatafromText {

    static ArrayList<Integer> no = new ArrayList<>();
    static ArrayList<String> name = new ArrayList<>();
    static ArrayList<String> address = new ArrayList<>();
    static ArrayList<String> gen = new ArrayList<>();
    static ArrayList<Long> sal = new ArrayList<>();
    static ArrayList<String> fname = new ArrayList<>();
    static ArrayList<String> lname = new ArrayList<>();

    static void print() {
        //int i;
        //System.out.print(no.get(i));
        for (int i = 0; i < 10; i++) {
            System.out.print(no.get(i) + "    ");
            System.out.print(name.get(i) + "  ");
            System.out.print(sal.get(i) + "   ");
            System.out.print(address.get(i) + "   ");
            System.out.print(gen.get(i) + "   ");
            System.out.println();

        }
    }
}

class DatafromSql {

    static ArrayList<Integer> ID = new ArrayList<>();
    static ArrayList<String> Fname = new ArrayList<>();
    static ArrayList<String> Lname = new ArrayList<>();
    static ArrayList<String> Gen = new ArrayList<>();
    static ArrayList<String> Address = new ArrayList<>();
    static ArrayList<Long> Sal = new ArrayList<>();

    static void print() {
        for (int i = 0; i < 10; i++) {
            System.out.print(ID.get(i) + "    ");
            System.out.print(Fname.get(i) + " ");
            System.out.print(Lname.get(i) + " ");
            System.out.print(Sal.get(i) + "   ");
            System.out.print(Address.get(i) + "   ");
            System.out.print(Gen.get(i) + "   ");
            System.out.println();

        }
    }
}

class DatafromExcel {

    static ArrayList<Integer> ID = new ArrayList<>();
    static ArrayList<String> Fname = new ArrayList<>();
    static ArrayList<String> Lname = new ArrayList<>();
    static ArrayList<String> Gen = new ArrayList<>();
    static ArrayList<String> Address = new ArrayList<>();
    static ArrayList<Long> Sal = new ArrayList<>();

    static void print() {
        for (int i = 0; i < 10; i++) {
            System.out.print(ID.get(i) + "    ");
            System.out.print(Fname.get(i) + " ");
            System.out.print(Lname.get(i) + " ");
            System.out.print(Sal.get(i) + "   ");
            System.out.print(Address.get(i) + "   ");
            System.out.print(Gen.get(i) + "   ");
            System.out.println();

        }
    }
}
