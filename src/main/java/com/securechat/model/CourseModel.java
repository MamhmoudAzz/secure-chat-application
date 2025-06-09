package com.securechat.model;

import java.util.Observable;

/**
 * Model class for managing course information in the application.
 * Handles course data and notifies observers of changes.
 */
public class CourseModel extends Observable {
    private int courseId;
    private String coursePath;

    /**
     * Creates a new course model with the specified ID and path.
     * 
     * @param courseId The unique identifier for the course
     * @param coursePath The file path to the course content
     */
    public CourseModel(int courseId, String coursePath) {
        this.courseId = courseId;
        this.coursePath = coursePath;
    }

    /**
     * Gets the course ID.
     * 
     * @return The course ID
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Sets the course ID and notifies observers.
     * 
     * @param id The new course ID
     */
    public void setCourseId(int id) {
        this.courseId = id;
        setChanged();
        notifyObservers();
    }

    /**
     * Gets the course file path.
     * 
     * @return The course file path
     */
    public String getCoursePath() {
        return coursePath;
    }

    /**
     * Sets the course file path and notifies observers.
     * 
     * @param coursePath The new course file path
     */
    public void setCoursePath(String coursePath) {
        this.coursePath = coursePath;
        setChanged();
        notifyObservers();
    }
}
