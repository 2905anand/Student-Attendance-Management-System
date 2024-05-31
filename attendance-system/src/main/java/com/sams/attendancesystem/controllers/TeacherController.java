package com.sams.attendancesystem.controllers;

// import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sams.attendancesystem.Repository.SubjectRepository;
import com.sams.attendancesystem.Repository.TeacherRepository;
import com.sams.attendancesystem.models.Subject;
import com.sams.attendancesystem.models.Teacher;
import com.sams.attendancesystem.util.PasswordEncodeDecode;

@RestController // This means that this class is a Controller
@RequestMapping(path="/teacher",method = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT}) // This means URL's start with /demo (after Application path)
public class TeacherController {
  
  @Autowired
  private TeacherRepository teacherRepository;

  @Autowired SubjectRepository subjectRepository;

  @PostMapping(path="/add") 
  public ModelAndView addNewTeacher (@ModelAttribute Teacher entity) {
    
    ModelAndView mav = new ModelAndView();
    try{
      Teacher teacher = new Teacher(entity.getteacherId(),entity.getteacherName());
      String encodedPassword = PasswordEncodeDecode.passwordEncoder(entity.getPassword());
      teacher.setPassword(encodedPassword);
      teacher.setTeacher_email(entity.getTeacher_email());
      teacher.setTeacher_role(entity.getTeacher_role());
      teacherRepository.save(teacher);
      mav.setViewName("successOperation");
      String message ="Teacher with Id "+teacher.getteacherId()+" and name "+teacher.getteacherName()+" added successfully!";
      mav.addObject("message",message);
    }
    catch(Exception ex){
      mav.setViewName("failOperation");
      mav.addObject("message", "Something Went Wrong!!");
      ex.printStackTrace();
    }
    return mav;
    
  }

  @PostMapping(value = "/createTeacherForSubject/{subjectCode}")
  public String createTeacherForSubject(@RequestBody Teacher entity, @PathVariable(name = "subjectCode") String subjectCode){
    // System.out.println("\n Create a new teacher and assign to an existing subject\n");

    Teacher teacher = new Teacher(entity.getteacherId(),entity.getteacherName());

    teacher = this.teacherRepository.save(teacher);

    Subject subject = this.subjectRepository.getReferenceById(subjectCode);

    Set<Teacher> teachers = subject.getTeachers();
    teachers.add(teacher);

    subject.setTeachers(teachers);

    subject = subjectRepository.save(subject);

    System.out.println("Teacher assigned to subject\n");

    return "Teacher Saved!!!";
  }

  @PostMapping(path = "/assignTeacherToSubject")
    public ModelAndView assignTeacherToSubject(@RequestParam(name="teachers") String teacherId, @RequestParam(name = "subjects") String subjectCode){
      // System.out.println("\n Create a new teacher and assign to an existing subject\n");

      // Teacher teacher = new Teacher(entity.getteacherId(),entity.getteacherName());

      // teacher = this.teacherRepository.save(teacher);
      // System.out.println("\nSaved Teacher :"+teacher+"\n");
      ModelAndView mav=new ModelAndView();

      try{

      Teacher teacher = this.teacherRepository.getReferenceById(teacherId);

      Subject subject = this.subjectRepository.getReferenceById(subjectCode);
      // System.out.println("\n Subject Details: "+subject.toString()+"\n");

      // Set<Subject> subjects = new HashSet<>();
      // subjects.add(subject);

      Set<Teacher> teachers = subject.getTeachers();
      teachers.add(teacher);
      // teachers.addAll(teachers)

      subject.setTeachers(teachers);

      subject = subjectRepository.save(subject);

      System.out.println("Teacher assigned to subject\n");
      mav.setViewName("successOperation");
      String message ="Subject "+subjectCode+" assigned to"+teacher.teacherName + " successfully!";
      mav.addObject("message",message);

      }catch(Exception e){
        mav.setViewName("failOperation");
        mav.addObject("message","Something went wrong!");
        e.printStackTrace();
      }

      // return "Teacher Assigned!!!";
      return mav;

    }

  // @GetMapping("/find/{teacherId}")
  // public Teacher find(@PathVariable Integer teacherId) {
    
  //   return teacherRepository.findById(teacherId);
  //     // .orElseThrow(() -> new TeacherNotFoundException(teacherId));
  // }

  @GetMapping(path = "/getTeacher/{teacherId}")
    public Teacher getTeacher(@PathVariable(name = "teacherId") String teacherId) {
      Teacher teacher = this.teacherRepository.getReferenceById(teacherId);
      
      return teacher;
  }
  

  // to Retrieve All Teachers
  @GetMapping(path="/all")
  public List<Teacher> getAll() {
    // This returns a JSON or XML with the users
    List<Teacher> teacherList = teacherRepository.findAll();
    //System.out.println(teacherList);
    return teacherList;
  }

  // @GetMapping(value = "/getTeacher/{teacherId}")
  //   public Teacher getEmployee(@PathVariable(name = "teacherId") String teacherId) {
  //       // System.out.println("Fetch Employee and Project details.");

  //       // get Employee details
  //       Teacher teacher = this.teacherRepository.getReferenceById(teacherId);
  //       System.out.println("\nTeacher details :: " + teacher.toString() + "\n");
  //       System.out.println("\nSubject details :: " + teacher.getSubjects() + "\n");

  //       System.out.println("Done!!!" + "\n");

  //       return teacher;
  //   }


  // to delete any Teacher using its id
  @DeleteMapping(path = "/delete/{teacherId}")
  public String delete(@PathVariable String teacherId){
    teacherRepository.deleteById(teacherId);
    return "Delete by id";
  }
}

