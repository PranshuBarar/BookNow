# BOOKNOW - An online movie ticketing application 
## (This repository contains the backend code for the BOOKNOW - An online movie ticketing application)

This is a Spring Boot application with around 19 APIs for performing all the CRUD operations related to ticketing of a movie show in a theater.
# Design of the Application
![image](https://user-images.githubusercontent.com/117909106/225555163-de58c469-7f1f-4473-b68a-a00ae4051e63.png)
![image](https://user-images.githubusercontent.com/117909106/225555601-eb9e1b22-aee2-4743-9acf-2745f8227699.png)

# Database Schema (EER Diagram)
![image](https://user-images.githubusercontent.com/117909106/225558558-376e21dd-8f01-4af5-8f58-b8d4bba940b8.png)

# Description
This Movie Booking application can do the following tasks : 
* USERS  
  * Users can register themselves on the application.
  * Users can deregister themselves.
  * Users can update their address. 
  * Get details of all the tickets booked by a particular user.  
* MOVIE
  * Add a new movie in the database.
  * Remove a movie from the database.
  * Get show time with the help of theaterId and movieId
  * Get Movie with max shows
  * Get Movie with max collection
  * Get list of all the movies with their total collection
  * Get total Collection of a Movie
* SHOW
  * Add a Show for a particular movie.
  * Cancel a show for a movie.
* THEATER
  * Add a new theater in the database.
  * Remove a theater from the database.
  * Get theaters with unique locations.
* TICKET
  * Users can book a ticket for the movie. (User can book multiple seats on the same ticket)
  * Users can cancel the ticket before the showdate.
  * Get details of a ticket using ticketId
  
# Steps to perform operations 
# Users : 
## => Registration of a User : 
![image](https://user-images.githubusercontent.com/117909106/224682460-66542e35-bb76-4dd1-9862-07c9f57da626.png)

## => De-registration of a User : 
![image](https://user-images.githubusercontent.com/117909106/224682066-29767601-6803-489f-abc2-da868787ea4c.png)

## => Updating address of a user : 
![image](https://user-images.githubusercontent.com/117909106/225541680-2b4daeef-e59d-433a-b996-e00ef054f2fb.png)

## => Getting details of all the tickets booked by a particular user till now : 
![image](https://user-images.githubusercontent.com/117909106/225540733-b24bfc4d-104b-4afc-b71c-cec9d0273a1a.png)
![image](https://user-images.githubusercontent.com/117909106/225540876-fb5e6b15-cede-4f46-b465-f1f92eb73db9.png)

# Movies : 
## => Adding a movie in the database : 
![image](https://user-images.githubusercontent.com/117909106/225545134-9b239479-1f70-4496-a038-dcfcd9a94a05.png)

## => Removing a movie from the database : 
![image](https://user-images.githubusercontent.com/117909106/225545392-cd89e987-518d-400b-b43d-3fb6240c8c4c.png)

## => Get show time with the help of theaterId and movieId:
![image](https://user-images.githubusercontent.com/117909106/225545633-8827f453-7aeb-4774-b7db-6ecaa3c01909.png)

## => Movie with max shows : 
![image](https://user-images.githubusercontent.com/117909106/225545781-1d0f5889-d8a2-4586-873d-a10df4c38c20.png)

## => Movie with max collection : 
![image](https://user-images.githubusercontent.com/117909106/225548380-73c247ea-0750-44f6-8bcf-6e51f215620d.png)

## => List of all the movies with their total collection : 
![image](https://user-images.githubusercontent.com/117909106/225548557-fcd09e68-e740-44a1-8a72-4731717f5a4a.png)

## => Total Collection of a Movie : 
![image](https://user-images.githubusercontent.com/117909106/225548766-c7cc83f9-1748-4425-9a40-6aa4d5e0649d.png)

## Tickets : 
## => Booking a ticket by user : 
![image](https://user-images.githubusercontent.com/117909106/225538866-05cfde4d-c379-4413-b982-da8a08c90b2c.png)

## => Cancellation of a ticket by user :
![image](https://user-images.githubusercontent.com/117909106/225539189-46829e4a-bc98-49b9-9f0c-8122c70b80ef.png)

## => If any other users tries to book the same seats which have been booked by any other user earlier : 
![image](https://user-images.githubusercontent.com/117909106/225540020-8e810a11-7d7d-4023-96ce-5d203406e14b.png)

## => Getting details of a ticket using ticketId
![image](https://user-images.githubusercontent.com/117909106/225542658-81035e26-2925-42a9-8070-51da2271c997.png)

# Show : 
## => Add Show : 
![image](https://user-images.githubusercontent.com/117909106/225536371-ee60ad0d-6296-43be-9449-7a97d373b848.png)

## => Remove Show : 
![image](https://user-images.githubusercontent.com/117909106/225537232-d5d2b08c-5556-4fe2-8e5c-150aafdf04fb.png)

# Theater : 
## => Adding Theater : 
![image](https://user-images.githubusercontent.com/117909106/225534903-a6470f3c-ecf5-4d4f-bbc1-489661a48460.png)

## => Removing Theater :
![image](https://user-images.githubusercontent.com/117909106/225535153-a9aae4a1-d2a4-4a61-9b4a-e17b9cef556b.png)

## => Theaters with unique locations :
![image](https://user-images.githubusercontent.com/117909106/225535437-931bfa30-a86e-449d-b0e1-028726ce9f5d.png)

# Tech Stack 
This project uses the following tech stack:
* Spring Boot
* Hibernate 
* MySQL WorkBench

## Contact
For questions, feedback, or support, please contact the project owner at pranshubarar1851996@gmail.com.

## Conclusion
This ONLINE-MOVIE-TICKETING-APPLCIATION provides a strong backend infrastructre to do CRUD operations and managing the database.
