package com.handson.basic.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.handson.basic.controller.StudentsController;
import com.handson.basic.model.PaginationAndList;
import com.handson.basic.model.SortDirection;
import com.handson.basic.model.StudentSortField;

import org.joda.time.LocalDate;

public class StudentSearch {
    private String fullName;
    private LocalDate fromBirthDate;
    private LocalDate toBirthDate;
    private Integer fromSatScore;
    private Integer toSatScore;
    private Integer fromAvgScore;
    private Integer page = 1;
    private Integer count = 100000;
    private StudentSortField sortField = StudentSortField.id;
    private SortDirection sortDirection = SortDirection.desc;

    public String getFullName() {
        return fullName;
    }

    public LocalDate getFromBirthDate() {
        return fromBirthDate;
    }

    public LocalDate getToBirthDate() {
        return toBirthDate;
    }

    public Integer getFromSatScore() {
        return fromSatScore;
    }

    public Integer getToSatScore() {
        return toSatScore;
    }

    public Integer getFromAvgScore() {
        return fromAvgScore;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getCount() {
        return count;
    }

    public StudentSortField getSortField() {
        return sortField;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public static final class StudentSearchBuilder {
        private String fullName;
        private LocalDate fromBirthDate;
        private LocalDate toBirthDate;
        private Integer fromSatScore;
        private Integer toSatScore;
        private Integer fromAvgScore;
        private Integer page = 1;
        private Integer count = 100000;
        private StudentSortField sortField = StudentSortField.id;
        private SortDirection sortDirection = SortDirection.desc;
        private StudentsController studentsController;

        public static StudentSearchBuilder aStudentSearch (StudentsController studentsController, String testId) {
            var res = new StudentSearchBuilder();
            res.studentsController = studentsController;
            res.fullName = testId;
            return res;
        }


        public StudentSearchBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public StudentSearchBuilder fromBirthDate(LocalDate fromBirthDate) {
            this.fromBirthDate = fromBirthDate;
            return this;
        }

        public StudentSearchBuilder toBirthDate(LocalDate toBirthDate) {
            this.toBirthDate = toBirthDate;
            return this;
        }

        public StudentSearchBuilder fromSatScore(Integer fromSatScore) {
            this.fromSatScore = fromSatScore;
            return this;
        }

        public StudentSearchBuilder toSatScore(Integer toSatScore) {
            this.toSatScore = toSatScore;
            return this;
        }

        public StudentSearchBuilder fromAvgScore(Integer fromAvgScore) {
            this.fromAvgScore = fromAvgScore;
            return this;
        }

        public StudentSearchBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public StudentSearchBuilder count(Integer count) {
            this.count = count;
            return this;
        }

        public StudentSearchBuilder sortField(StudentSortField sortField) {
            this.sortField = sortField;
            return this;
        }

        public StudentSearchBuilder sortDirection(SortDirection sortDirection) {
            this.sortDirection = sortDirection;
            return this;
        }

        public PaginationAndList execute() throws JsonProcessingException {
            StudentSearch studentSearch = new StudentSearch();
            studentSearch.fromSatScore = this.fromSatScore;
            studentSearch.toSatScore = this.toSatScore;
            studentSearch.sortField = this.sortField;
            studentSearch.fromBirthDate = this.fromBirthDate;
            studentSearch.count = this.count;
            studentSearch.toBirthDate = this.toBirthDate;
            studentSearch.fullName = this.fullName;
            studentSearch.fromAvgScore = this.fromAvgScore;
            studentSearch.sortDirection = this.sortDirection;
            studentSearch.page = this.page;
            return studentsController.search(fullName,fromBirthDate, toBirthDate, fromSatScore, toSatScore,
                    fromAvgScore, page, count, sortField, sortDirection).getBody();
        }
    }
}
