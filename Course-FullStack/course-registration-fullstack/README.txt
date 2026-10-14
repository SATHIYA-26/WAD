COURSE REGISTRATION - FULL STACK
Vue 3 + Spring Boot + MongoDB

REQUIREMENTS
- Java 17+
- Maven (or use your IDE's Maven support)
- Node.js and npm
- MongoDB running locally on port 27017

BACKEND
1. Ensure MongoDB is running.
2. Open a terminal in backend.
3. Run: mvn spring-boot:run
   Or open backend/pom.xml in IntelliJ IDEA and run CourseRegistrationApplication.
4. API base URL: http://localhost:8080/api/courses

FRONTEND
1. Open another terminal in frontend.
2. Run: npm install
3. Run: npm run dev
4. Open the URL printed by Vite, usually http://localhost:5173.

FEATURES
- Register a student for a course
- View all registrations
- Search registrations by student name or course
- Delete a registration
- MongoDB persistence

IMPORTANT
- MongoDB must be running for backend API calls to work.
- This is a student-friendly lab project, not production-hardened.
- CORS allows the Vite development server at http://localhost:5173.
- If you use a different MongoDB URI, change backend/src/main/resources/application.properties.
