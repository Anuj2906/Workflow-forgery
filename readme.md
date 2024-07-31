# Workflow Creation Project

This project is a workflow creation application with which we can create workflows and execute any created workflow on user input data and show workflow execution chart using React, React Flow, MongoDB, and Spring Boot.

## Features

- Create, visualize, and manage workflows
- Interactive workflow diagrams with React Flow
- MongoDB for data storage
- Spring Boot for backend services

## Technologies Used

- **Frontend:** React, React Flow, Redux, React Bootstrap
- **Backend:** Spring Boot, MongoDB

## Installation

### Frontend

1. Clone the repository:

    ```bash
   git clone https://github.com/Anuj2906/Workflow-forgery
   cd Workflow-forgery/frontend
    ```
2. Install dependencies:

    ```bash
    npm install
    ```
3. Start the development server:
    ```bash
    npm start
    ```

### Backend

1. Navigate to the backend directory:
 ```bash
 cd ../backend
```
2. Open it in Intellij ide and ensure that you have installed Java and Maven.

3. Add mongodb uri in **application.properties** file.

4. Now open **WorkflowManagementApplication.java** file and run it.

## Usage : 
Open your browser and navigate to http://localhost:3000 to access the frontend.
The application connects to the backend running on http://localhost:8080 to fetch and manage workflow data.
