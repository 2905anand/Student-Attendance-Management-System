package com.sams.attendancesystem.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
// import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sams.attendancesystem.Repository.BranchRepository;
import com.sams.attendancesystem.Repository.StudentRepository;
import com.sams.attendancesystem.Repository.SubjectRepository;
import com.sams.attendancesystem.models.Branch;
import com.sams.attendancesystem.models.Student;
import com.sams.attendancesystem.models.Subject;
import com.sams.attendancesystem.models.Teacher;

import jakarta.servlet.http.HttpServletRequest;
import jxl.Workbook;

@RestController
@RequestMapping(path = "/student", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class StudentController {
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private BranchRepository branchRepository;

    // Function to add New Students @RequestParam Integer studentId
    @PostMapping(path = "/add")
    public ModelAndView addNewStudent(@ModelAttribute Student entity){
        ModelAndView mav=new ModelAndView();
        try{
            Student student = new Student(entity.getstudentId(), entity.getstudentRollNo(), entity.getstudentName(), entity.getbatch(), entity.getbranchId(), entity.getsemester());
            studentRepository.save(student);
           mav.setViewName("successOperation");
           String message ="Student with Id "+student.getstudentId()+" and name "+student.getstudentName()+" added successfully!";
            mav.addObject("message",message);
        }catch(Exception exc){
            mav.setViewName("failOperation");
            mav.addObject("message", "Something Went Wrong!!");
            exc.printStackTrace();
            
            
        }
        return mav;
    }

    @PostMapping("/addStudents")
    public String addMultipleStudents(HttpServletRequest request,@RequestParam("file") MultipartFile multipartFile) throws Exception{
        ModelAndView mav=new ModelAndView();
        List<Student> studentList = new ArrayList<Student>();
        try{
            File file = new File("src/main/resources/target.xlsx");
            byte[] bytes = multipartFile.getBytes();
            
            //File file=new File(destination.)
            /*for(Row row:sheet){
                
                String studentId = row.getCell(1).toString();
                int batch = Integer.parseInt(row.getCell(1).getStringCellValue());
                String branchId = row.getCell(2).getStringCellValue();
                int semester =Integer.parseInt(row.getCell(3).getStringCellValue());
                String studentName = row.getCell(4).getStringCellValue();
                int studentRollNo = Integer.parseInt(row.getCell(5).getStringCellValue());

                Student student=new Student(studentId,studentRollNo,studentName,batch,branchId,semester);
                studentList.add(student);
             }
             
             for (Student student : studentList){
                studentRepository.save(student);
             }*/

        }catch(Exception e){
            e.printStackTrace();
        }
        return "Done";
    }

    @PostMapping(path = "/assignStudentToSubject")
    public ModelAndView assignSubjectsToStudent(@RequestParam(name = "students") String studentId, @RequestParam(name = "subjects") List<String> subjects) {
        ModelAndView mav=new ModelAndView();
        try{
        Student student = this.studentRepository.getReferenceById(studentId);

        for(String subjectCode:subjects){
        Subject subject = this.subjectRepository.getReferenceById(subjectCode);

        Set<Student> students = subject.getStudents();
        students.add(student);

        // Set<Subject> subjects = student.getSubjects();
        // subjects.add(subject);

        subject.setStudents(students);

        subject = subjectRepository.save(subject);

        }
        mav.setViewName("successOperation");
        String message = "Subjects assigned to "+studentId+" successfully!";
        mav.addObject("message", message);
    }catch(Exception e){
        mav.setViewName("failOperation");
        mav.addObject("message", "Something Went Wrong!!");
        e.printStackTrace();
    }
        return mav;

    }

    // Function to Retrieve All Subjects
    @GetMapping(path = "/all")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    // Find Student by name
    @GetMapping(path = "/name/{studentName}")
    public List<Student> getStudent(@PathVariable String studentName){
        List<Student> students = studentRepository.findByStudentName(studentName);
        return students;
    }

    // Find Student by Roll No.
    @GetMapping(path = "/rollNo/{studentRollNo}")
    public List<Student> getStudentbyRoll(@PathVariable Integer studentRollNo){
        List<Student> students = studentRepository.findByStudentRollNo(studentRollNo);
        return students;
    }

    public Student getStudentbyId(@PathVariable String studentId){
        List<Student> students = studentRepository.findByStudentId(studentId);
        if(students!=null){
            return students.get(0);
        }
        return null;
    }


    // Function to delete any Student using its ID
    @DeleteMapping(path="delete/{studentId}")
    public String  deleteStudent(@PathVariable String studentId){
        studentRepository.deleteById(studentId);
        return "Student Deleted";
    }

    public List<Student> getStudentListForAttendance(String course,String branch,int semesterValue) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getStudentListForAttendance'");
        List<Student> studentList=studentRepository.findStudentforAttendance(course, branch, semesterValue);
        return studentList;
    }

}
