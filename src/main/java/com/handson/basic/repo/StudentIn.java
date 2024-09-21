package com.handson.basic.repo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.handson.basic.model.Student;
import com.handson.basic.util.Dates;
import org.hibernate.validator.constraints.Length;
import org.joda.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.io.Serializable;

import static com.handson.basic.model.Student.StudentBuilder.aStudent;

public class StudentIn implements Serializable {

    @Length(max = 60)
    private String fullname;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;

    @Min(100)
    @Max(800)
    private Integer satScore;

    @Min(30)
    @Max(110)
    private Double graduationScore;

    @Length(max = 20)
    private String phone;


    public Student toStudent() {
        return aStudent().birthDate(Dates.atUtc(birthDate)).fullname(fullname)
                .satScore(satScore).graduationScore(graduationScore)
                .phone(phone)
                .build();
    }

    public void updateStudent(Student student) {
        student.setBirthDate(Dates.atUtc(birthDate));
        student.setFullname(fullname);
        student.setSatScore(satScore);
        student.setGraduationScore(graduationScore);
        student.setPhone(phone);
    }

    public String getFullname() {
        return fullname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getSatScore() {
        return satScore;
    }

    public Double getGraduationScore() {
        return graduationScore;
    }

    public String getPhone() {
        return phone;
    }
}
