# Conveyor Belt Simulator

Java program with GUI created in JavaFX that shows simulations of conveyor belt. The program operating on multi-threading solves the problem of starvation and jamming (the problem of producers and consumers). Program prevents the deadlock and provide synchronization  

## Design assumptions

There are three employees working on the conveyor belt, marked with P1, P2 and P3. Workers put bricks with a mass of 1,2 and 3 units. At the end of the belt there is a truck with a C load capacity, which should always be fully loaded before leaving. All employees try to stack bricks on the belt as quickly as possible. The belt can transport a maximum of K bricks at a time. After the truck is full, a new one with the same parameters appears immediately in the same place.    

## Features

- Logging in
- Set truck capacity
- Set conveyor belt capacity
- Set maximum amount of brick on conveyor belt

## Used Technologies

- Java
- JavaFX
- Scene Builder
