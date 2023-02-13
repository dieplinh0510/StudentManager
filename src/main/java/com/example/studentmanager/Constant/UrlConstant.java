package com.example.studentmanager.Constant;


public class UrlConstant {
  public static class Subject {
    public static final String PRE_FIX = "/subjects";
    public static final String GET_ALL_SUBJECT = PRE_FIX;
    public static final String GET_SUBJECT = PRE_FIX + "/{subjectId}";
    public static final String CREATE_SUBJECT = PRE_FIX + "/create";
    public static final String CHANGE_SUBJECT = PRE_FIX + "/change/{subjectId}";
    public static final String DELETE_SUBJECT = PRE_FIX + "/delete/{subjectId}";

    public Subject() {
    }
  }

  public static class Calendar {
    public static final String PRE_FIX = "/calendars";
    public static final String GET_ALL_CALENDAR = PRE_FIX;
    public static final String GET_CALENDAR = PRE_FIX + "/{calendarId}";
    public static final String GET_CALENDARS_BY_SUBJECT = PRE_FIX + "/subject/{subjectId}";
    public static final String CREATE_CALENDAR = PRE_FIX + "/create";
    public static final String CHANGE_CALENDAR = PRE_FIX + "/change/{calendarId}";
    public static final String DELETE_CALENDAR = PRE_FIX + "/delete/{calendarId}";

    public Calendar() {
    }
  }


  public static class Notification {
    public static final String PRE_FIX = "/notifications";
    public static final String GET_ALL_NOTIFICATION = PRE_FIX;
    public static final String GET_NOTIFICATION = PRE_FIX + "/{notificationId}";
    public static final String CREATE_NOTIFICATION = PRE_FIX + "/create";
    public static final String CHANGE_NOTIFICATION = PRE_FIX + "/change/{notificationId}";
    public static final String DELETE_NOTIFICATION = PRE_FIX + "/delete/{notificationId}";

    public Notification() {
    }
  }

  public static class Semester {
    public static final String PRE_FIX = "/semesters";
    public static final String GET_ALL_SEMESTER = PRE_FIX;
    public static final String GET_SEMESTER = PRE_FIX + "/{semesterId}";
    public static final String CREATE_SEMESTER = PRE_FIX + "/create";
    public static final String CHANGE_SEMESTER = PRE_FIX + "/change/{semesterId}";
    public static final String DELETE_SEMESTER = PRE_FIX + "/delete/{semesterId}";
    public static final String GET_SUBJECTS_INSEMESTER = PRE_FIX+ "subjectInSemester/{semesterId}";

    public Semester() {
    }
  }

  public static class Auth {
    public static final String PRE_FIX = "/auth";
    public static final String LOGIN = PRE_FIX + "/login";
    public static final String SIGNUP = PRE_FIX + "/signup";
    public static final String VERIFY_SIGNUP = SIGNUP + "/verify";
    public static final String LOGOUT = PRE_FIX + "/logout/{id}";
    public static final String VALIDATE = PRE_FIX + "/validate";
    public Auth() {
    }
  }

  public static class User {
    public static final String PRE_FIX = "/users";
    public static final String GET_ALL_USER = PRE_FIX;
    public static final String GET_USER = PRE_FIX + "/{userId}";
    public static final String CHANGE_USER = PRE_FIX + "/change/{userId}";
    public static final String DELETE_USER = PRE_FIX + "/delete/{userId}";

    public User() {
    }
  }

  public static class Role {
    public static final String PRE_FIX = "/roles";
    public static final String GET_ALL_ROLE = PRE_FIX;
    public static final String GET_ROLE = PRE_FIX + "/{roleId}";
    public static final String CREATE_ROLE = PRE_FIX + "/create";
    public static final String CHANGE_USER = PRE_FIX + "/change/{roleId}";
    public static final String DELETE_USER = PRE_FIX + "/delete/{roleId}";

    public Role() {
    }
  }

  public static class StudentSubject{
    public static final String PRE_FIX = "/studentsubject";
    public static final String GET_ALL_STUDENTSUBJECT = PRE_FIX;
    public static final String GET_STUDENTSUBJECT = PRE_FIX + "/{studentSubjectId}";
    //dang ki hoc phan
    public static final String CREATE_STUDENTSUBJECT = PRE_FIX + "/create/{userId}/{subjectId}";

    public static final String CHANGE_STUDENTSUBJECT = PRE_FIX + "/change/{studentsubjectId}";
    public static final String DELETE_STUDENTSUBJECT = PRE_FIX + "/delete/{studentsubjectId}";

    public static final String GET_SCORE = PRE_FIX + "/score/{userId}/{subjectId}";
  }

}
