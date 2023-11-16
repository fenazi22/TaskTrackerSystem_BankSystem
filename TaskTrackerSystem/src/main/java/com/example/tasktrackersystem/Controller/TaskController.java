package com.example.tasktrackersystem.Controller;

import com.example.tasktrackersystem.ApiResonse.ApiResonse;
import com.example.tasktrackersystem.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {


    ArrayList<Task> tasks = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Task> getTasks() {
        return tasks;
    }


    @PostMapping("/add")
    public ArrayList<Task> addTasks(@RequestBody Task task) {
        task.setStatus("not done");
        tasks.add(task);
        return tasks;
    }




    @PutMapping("/update/{index}")
    public ArrayList<Task> updateTasks(@PathVariable int index, @RequestBody Task task) {
        tasks.set(index, task);
        return tasks;
    }


    @DeleteMapping("/delete/{index}")
    public ArrayList<Task> delete(@PathVariable int index) {
        tasks.remove(index);
        return tasks;
    }





    @PutMapping("/change/{index}")
    public ApiResonse changeStauts(@PathVariable int index) {
        Task task = tasks.get(index);

        if (task.getStatus().equals("done")) {
            task.setStatus("not done");
            tasks.set(index, task);
            return new ApiResonse("200 ");
        } else {
            task.setStatus("done");
            tasks.set(index, task);
            return new ApiResonse("200 ");

        }
    }


    @GetMapping("/search/{titles}")
    public ApiResonse searchTask(@PathVariable String titles) {

        for (Task search : tasks) {
            if (search.getTitle().equals(titles)) {
                return new ApiResonse(" 200  " + search);
            }
        }
        return new ApiResonse(" 404 ");
    }




}
