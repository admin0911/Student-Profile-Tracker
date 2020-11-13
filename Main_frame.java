import java.io.*;

import javax.swing.*;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.text.DecimalFormat;
import java.util.*;

class student_details
{
    private int flag_val=-1;
    private String name,roll_no;
    private double marks[]=new double[9];
    private String course[]=new String[9];
    private double max_marks[]=new double[9];
    private double avg_score[]=new double[9];
    student_details()
    {
        this.roll_no="";
        fetch_course_details();
    }
    student_details(String roll_no)
    {
        this.roll_no=roll_no;
        fetch_student_details();
        fetch_course_details();
    }
    private void fetch_course_details()
    {
        try
        {
            FileInputStream fis=new FileInputStream(new File("C:\\Users\\adhij\\Downloads\\java project\\Assessment.xlsx"));
            try
            {
                XSSFWorkbook wb = new XSSFWorkbook(fis);
                XSSFSheet sheet = wb.getSheetAt(0);
                for(int loop=0;loop<9;loop++)
                {
                    try {
                        this.avg_score[loop] = sheet.getRow(4).getCell(loop + 3).getNumericCellValue();
                    }
                    catch(IllegalStateException e)
                    {
                        System.out.println("VALUE NOT FOUND");
                    }
                    this.course[loop]=sheet.getRow(1).getCell(loop + 3).getStringCellValue();
                    this.max_marks[loop]=sheet.getRow(2).getCell(loop + 3).getNumericCellValue();
                }
            }
            catch(IOException e)
            {
                System.out.println("INPUT OUTPUT EXCEPTION OCCURED");
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("FILE NOT FOUND");
        }
    }
    private void fetch_student_details()
    {
        try
        {
            FileInputStream fis=new FileInputStream(new File("C:\\Users\\adhij\\Downloads\\java project\\Assessment.xlsx"));
            try
            {
                XSSFWorkbook wb = new XSSFWorkbook(fis);
                XSSFSheet sheet = wb.getSheetAt(0);
                for(int loop=5;loop<sheet.getPhysicalNumberOfRows();loop++)
                {
                    if(roll_no.equalsIgnoreCase(sheet.getRow(loop).getCell(0).getStringCellValue()))
                    {
                        flag_val=loop;
                    }
                }
                if(flag_val!=-1)
                {
                    this.name=sheet.getRow(flag_val).getCell(1).getStringCellValue();
                    for(int loop=0;loop<9;loop++)
                    {
                            this.marks[loop] = sheet.getRow(flag_val).getCell(loop + 3).getNumericCellValue();
                    }
                }
            }
            catch(IOException e)
            {
                System.out.println("INPUT OUTPUT EXCEPTION OCCURED");
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("FILE NOT FOUND");
        }
    }
    double[] getmarks()
    {
        return marks;
    }
    void setmarks(double[] new_marks)
    {
        try
        {
            FileInputStream fis=new FileInputStream(new File("C:\\Users\\adhij\\Downloads\\java project\\Assessment.xlsx"));
            try
            {
                XSSFWorkbook wb = new XSSFWorkbook(fis);
                XSSFSheet sheet = wb.getSheetAt(0);
                for(int loop=0;loop<9;loop++)
                {
                    sheet.getRow(flag_val).getCell(loop + 3).setCellValue(new_marks[loop]);
                }
                fis.close();
                try {
                    FileOutputStream fos = new FileOutputStream("C:\\Users\\adhij\\Downloads\\java project\\Assessment.xlsx");
                    wb.write(fos);
                    fos.close();
                }
                catch(FileNotFoundException e)
                {
                    System.out.println("FILE NOT FOUND");
                }
            }
            catch(IOException e)
            {
                System.out.println("INPUT OUTPUT EXCEPTION OCCURED");
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("FILE NOT FOUND");
        }
        fetch_student_details();
    }
    double[] getavg_score()
    {
        return avg_score;
    }
    String[] getcourse()
    {
        return course;
    }
    double[] getmax_marks()
    {
        return max_marks;
    }
    String getname()
    {
        return name;
    }
    String getroll_no()
    {
        return roll_no;
    }
}

class student extends student_details  //will be called when u are logging in as a student.
{
    student(String roll_no)
    {
        super(roll_no);
        details();
    }

    void CgpaCalc(double[] grade, double[] credits, double prev, int n)
    {

        double curr, avg, sumcredits = 0;
        double sum=0;
        for (int i = 0; i < n; i++) {

            sum=sum+(grade[i]*credits[i]);
            sumcredits= sumcredits+credits[i];
        }
        curr=sum/sumcredits;
        avg=(prev+curr)/2;
        System.out.println(
                "CGPA Percentage = "
                        + String.format("%.2f", avg));
        student stud = new student(super.getroll_no());
    }
    void details()
    {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df=new DecimalFormat("##.#");
        third second1 = new third();
        second1.setVisible(true);
        JOptionPane.showMessageDialog(null,"\"You have sucessfuly logged in as \""+super.getroll_no());

        System.out.println("You have sucessfuly logged in as "+super.getroll_no());
        System.out.println("\nOperations available \n 1] View marks\n 2] Calculate CGPA\n 3] Exit");
        int ch;
        ch = sc.nextInt();
        while(ch<0 || ch>3)
        {
            System.out.println("Invalid input. Please enter a valid option");
            ch = sc.nextInt();
        }
        if( ch ==1)
        {
            System.out.println("Name: "+super.getname());
            String[] temp_course=getcourse();
            double[] temp_max_marks=getmax_marks();
            double[] temp_marks=getmarks();
            for(int loop=0;loop<9;loop++)                               //gets course and marks and displays it
            {
                System.out.println(temp_course[loop]+"\n"+"Maximum marks : "+df.format(temp_max_marks[loop])+"       Marks Obtained : "+df.format(temp_marks[loop]));
            }

            student stud = new student(super.getroll_no());
        }
        else if( ch == 2)
        {
            System.out.println("Calculating CGPA");                 //Future update - > Get credits from Excel sheet
            Scanner in=new Scanner(System.in);
            String[] temp_course=getcourse();

            int n = 9;
            int i ;

            String[] subjects = temp_course;
            double grade[] = new double[n];
            System.out.println(" ENTER YOUR grade IN EACH SUBJECTS : ");
            for (i=0;i<n;i++)
            {
                System.out.println(subjects[i]);
                grade[i]= in.nextInt();
            }

            double[] credits = new double[n];
            System.out.println(" ENTER THE CREDIT OF EACH SUBJECT : ");
            for (i=0;i<n;i++)
            {
                System.out.println(subjects[i]);
                credits[i]= in.nextInt();
            }
            System.out.println(" ENTER YOUR PREVIOUS SEMESTER CGPA : ");
            double prev = in.nextDouble();

            CgpaCalc(grade, credits, prev, n);

        }
        else
        {
            Main_frame m = new Main_frame();
            m.main(null);
        }
    }
}

class tutor     //will be called when u are logging in as a tutor.
{
    private String tutor_registered_number;
    tutor(String tutor_registered_number)
    {
        this.tutor_registered_number=tutor_registered_number;
        menu();
    }
    void menu()
    {
        Scanner sc=new Scanner(System.in);
        int choice;
        String roll_no;
        secondbox second = new secondbox();
        second.setVisible(true);
        JOptionPane.showMessageDialog(null,"\nYou have succesfully logged in as Tutor");
        System.out.println("\nYou have succesfully logged in as Tutor");
        System.out.print("\nChoose option from the below menu \n1) Check student details \n2) Edit student marks \n3) Class average performance\n4) Exit\n");
        choice=sc.nextInt();
        while(choice<=0 || choice>4)
        {
            System.out.println("Invalid input. Please enter a valid option");
            choice = sc.nextInt();
        }
        switch(choice)
        {
            case 1:System.out.println("\nEnter student roll number\n");         //displays details of a particular student
                    roll_no=sc.next();
                    student_det(roll_no);
                    menu();
                    break;
            case 2: System.out.println("\nEnter student roll number\n");        ///used to edit detaisl of a student
                    roll_no=sc.next();
                    edit_student_marks(roll_no);
                    menu();
                    break;
            case 3: class_avg_performance();                                    //
                    menu();
                    break;
            case 4: Main_frame m = new Main_frame();
                    m.main(null);
        }
    }
    void edit_student_marks(String roll_no)                         ///Future update - for particular subject alone change
    {
        student_details s=new student_details(roll_no);
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the updated marks according to the subjects given below : \n");
        String[] temp_course=s.getcourse();
        double[] new_marks=new double[9];
        for(int loop=0;loop<9;loop++)
        {
            System.out.print("Enter marks for "+temp_course[loop]+" : ");
            new_marks[loop]=sc.nextDouble();
            if(new_marks[loop]<0 || new_marks[loop]>20)
            {
                System.out.println("Marks should be in ther range of 0 and 20");
                new_marks[loop]=sc.nextDouble();
            }
        }
        s.setmarks(new_marks);
        System.out.println("Marks after updating : ");
        student_det(roll_no);
    }
    void class_avg_performance()
    {
        student_details s=new student_details();
        DecimalFormat df=new DecimalFormat("##.#");
        String[] temp_course=s.getcourse();
        double[] temp_max_marks=s.getmax_marks();
        double[] temp_avg_score=s.getavg_score();
        for(int loop=0;loop<9;loop++)
        {
            System.out.println(temp_course[loop]+"\n"+"Maximum marks : "+df.format(temp_max_marks[loop])+"       Average Marks Obtained By Class : "+df.format(temp_avg_score[loop]));
        }
    }
    void student_det(String roll_no)
    {
        student_details s=new student_details(roll_no);
        DecimalFormat df=new DecimalFormat("##.#");
        System.out.println(s.getname());
        System.out.println(s.getroll_no());
        String[] temp_course=s.getcourse();
        double[] temp_max_marks=s.getmax_marks();
        double[] temp_marks=s.getmarks();
        for(int loop=0;loop<9;loop++)
        {
            System.out.println(temp_course[loop]+"\n"+"Maximum marks : "+df.format(temp_max_marks[loop])+"       Marks Obtained : "+df.format(temp_marks[loop]));
        }
    }
}

class login
{
    private String username,password;
    private int type;
    login(String username,String password,int type)
    {
        this.username=username;
        this.password=password;
        this.type=type;
        authentication();
    }
    void authentication()
    {
        int receive=check_if_valid();               ///this is called firt to check if such a user exits
        if(receive == 1 && type ==1)
        {
            student_details s=new student(username);
        }
        else if(receive == 1 && type ==2)
        {
            tutor t=new tutor(username);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"INVALID USERNAME OR PASSWORD.\nENTER VALID USERNAME AND PASSWORD");
        }
    }
    private int check_if_valid()
    {
        if(type==1)
        { try {
            File file = new File("C:\\Users\\adhij\\Downloads\\java project\\student_pass.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] user_name_pass = sc.nextLine().split("\\s");
                if (user_name_pass[0].equals(username) && user_name_pass[1].equals(password))
                {
                    return 1;
                }
            }
            return 0;
        }
        catch(FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            return -1;
        }
        }
        else {
            try {
                File file = new File("C:\\Users\\adhij\\Downloads\\java project\\tutor_pass.txt");
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String[] user_name_pass = sc.nextLine().split("\\s");
                    if (user_name_pass[0].equals(username) && user_name_pass[1].equals(password))
                    {
                        return 1;
                    }
                }
                return 0;
            }
            catch(FileNotFoundException e)
            {
                System.out.println("An error occurred.");
                return -1;
            }
        }
    }
}
public class Main_frame {
    public static void main(String[] args)
    {
        String username,password;
        int exit = 0;
        Scanner sc=new Scanner(System.in);
        System.out.println("Who are you logging in as\n 1] Student \n 2] Tutor\n ");
        int type;
        do
        {
            type = sc.nextInt();
            if(type==1 || type==2)
            {
                exit = 1;
            }
            else
            {
                System.out.println("Invlaid input.Please enter a valid option\n");
            }
        }
        while(exit == 0);
        System.out.println("Enter username: ");
        username=sc.next();
        System.out.println("Enter password: ");
        password=sc.next();

        login l=new login(username,password,type);
    }
}

