# Queue Management System

## Application Description📋
The Queue Management System is a simulation of a queuing system that assigns clients to queues in such a way as to minimize their waiting time. The main goal of the application is to simulate the behavior of queues and efficiently allocate clients to servers, considering their arrival and service times. The application includes an intuitive graphical user interface that allows users to enter simulation parameters and view the results dynamically.

## Objectives🎯
- Implement a simulation of a queuing system, including the assignment of clients to queues in an optimal manner.
- Create an interface for entering and viewing simulation parameters and results (total time spent in queues and average waiting time).
- Optimize the algorithm for assigning clients to queues based on strategies such as **SHORTEST_TIME** and **SHORTEST_QUEUE**.

## Main Features⭐
- **Queuing System Simulation**: The application allows generating a number of clients and assigning them to queues optimally.
- **Client Distribution Strategies**: The application uses two main strategies:
  - **SHORTEST_TIME**: Assigns clients to the server with the shortest waiting time.
  - **SHORTEST_QUEUE**: Assigns clients to the server with the shortest queue.
- **Client Generation**: Allows for random client generation with specified arrival and service time intervals.
- **Graphical Interface**: Allows real-time visualization of queues and the simulation progress.

## Technologies Used⚙️
- **Java** for implementing the application.
- **Swing** for creating the graphical user interface (GUI).
- **Threading**: The application uses threads to simulate the real-time behavior of servers in the queuing system.
- **BlockingQueue** and **Lists** for managing the queue and distributing clients.
- **Atomic variables** and **synchronization** to ensure the correctness of operations in a multithreading environment.
