## Requirements
  - Java 17 or newer
## Installing
  You can download jar or build one yourself
### Download jar
  - [Download link](https://github.com/Furestry/FabriqueTest/releases)
### Download sources
  After downloading you need follow these steps:
  - Unzip source code
  - Go to the source directory
  - Use the console command 'gradlew build'
  - Your jar file will be in the source directory/build/libs folder and named FabriqueTestApp-Version.jar
## Launching
To run the app you need to run the console command 'java -jar fileName'. You can use [options](https://www.oracle.com/java/technologies/javase/vmoptions-jsp.html) as desired.
## Docs
You can find the docs at [link](https://furestry.github.io/FabriqueTest/)
## Using
To manipulate (Create/Delete/Edit) surveys you must send authorization with admin login and his password.
### Change password
By default, in app admin password is '12345'. You can change his in 'application.properties' and rebuild jar with steps in 'Download sources'.
### Create Survey
To create survey you need send Put request to /admin/ endpoint with parameters:
- id as long
- description as String
- startTime in ISO format (yyyy-MM-ddTHH:mm)
- endTime in ISO format  
  
and with request body with questions like:

```json
[
  {
    "id": "Question id as long",
    "text": "Question text",
    "type": "Question type from QuestionType class like TEXT"
  }
]
```
### Create User
To create a user, just send a Get request to the /user endpoint with the id parameter, and if the user is not found, it will be created.
### Edit Survey param
To edit survey param you need send Post request with request body like:
```json
{
  "id": "Survey id as long",
  "param": "description",
  "value": "Text of description as String"
}
```
Param can be description/questions/endTime.  
You cannot edit the start time and must keep the value correct. For example, the description should be a String, the end time should be an ISO string, and the list of questions should be a list as in Create Survey
### Delete Survey
To delete survey you need send Delete request to the /admin/ endpoint with the id parameter
### Send Survey
To send survey you need send Post request to /survey endpoint with request body like:

```json
{
  "userId": "User id can be a new id",
  "surveyId": "Active survey id",
  "answers": [
    {
      "questionId": "The question id contained in the list of survey questions, as long",
      "answer": "Answer as String"
    }
  ]
}
```
### Get User
To get user you need send Get request to the /user endpoint with id parameter
### Get active Surveys
To get active surveys you need send Get request to /surveys endpoint.
### Get Survey
To get active surveys you need send Get request to /survey endpoint with id parameter.