package org.example;


import java.util.ArrayList;
import java.util.List;

class Task {
    public void run(){
        System.out.println("Running: " + this);
    }
}

public class CustomAsync {

    List<Task> tasks = new ArrayList<>();

    public void submitTask(Task task){
        System.out.println("Submitted: " + task);
        tasks.add(task);
    }

    public static void main(String[] args) {

        CustomAsync runner = new CustomAsync();

        for (int i = 0; i < 5; i++) {
            runner.submitTask(new Task());
        }

        runner.tasks.forEach(task -> task.run());

    }


}
