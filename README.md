# OOP-final-project
Simple text-based social media app. Final project for Object Oriented Programming, New York University, Summer 2022.

**Authors**: Ericsson Colborn, Joey Lalo, Zachary Mokhtarzadeh

Thank you to [Diogo Nunes](https://github.com/dialex) for creating their open-source package [JColor](https://github.com/dialex/JColor), which we used to color the text of each user's username. We couldn't have done it without you.


# Overview

We have made a console-operated social media platform for our project. This system is similar to any text-based social media platform like Reddit, Twitter, Facebook, etc. and as such, will be built around two core objects: Users and Posts. The Admin and Moderator classes extend the User class and grant additional permissions and functionalities. 

### Actors and Objects

**Actors**: Users, Moderators, Admins

**Objects**: Post, User → [Moderator, Admin]

The Post object will store the following information: the text of the post, the post id, the user that posted, and the post itself. Users can create a post or delete their own posts. Moderators have the ability to delete other users’ posts that they deem inappropriate. Unlike Moderators, Admins have the ability to remove posting privileges from certain users. Moderators and Admins share the same posting abilities as the standard User.

The menu will have standard options for social media. Once logged in it will prompt the user to choose from the following options: “Create Post”, “Delete Post”, “Show Your Posts”, “Show all Posts”, or “Log Out”. If the instance of the user is a Moderator, if the “Delete Post” option is selected it will prompt the Moderator to delete one of their own posts or any of the user’s posts. If the instance of the User is an Admin the main menu will have an extra option called “Set Post Privileges of User” and this will allow the Admin to stop Users from being able to post.

### Goals and Benefits 

1. At the most basic level, to create a system that enables create, read, update and delete (CRUD) operations for multiple users
2. More specifically, to create an easy to use and straight forward text-based social media platform to connect with friends and demonstrate our understanding of object oriented principles. 
