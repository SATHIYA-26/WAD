package com.example.courseregistration;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRegistrationRepository
        extends MongoRepository<CourseRegistration, String> {
}