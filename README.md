# spring-login
Integrate the different login way. 

# Build react front end and add it to spring app
cd ticket-tracker

npm run build 

Copy-Item -Path ".\build\*" -Destination "..\orgahive\src\main\resources\static\" -Recurse

