package ru.tsu.hits.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tsu.hits.csv.CommentCsv;
import ru.tsu.hits.csv.ProjectCsv;
import ru.tsu.hits.csv.TaskCsv;
import ru.tsu.hits.csv.UserCsv;
import ru.tsu.hits.entity.CommentEntity;
import ru.tsu.hits.entity.ProjectEntity;
import ru.tsu.hits.entity.TaskEntity;
import ru.tsu.hits.entity.UserEntity;
import ru.tsu.hits.exception.NotFoundException;
import ru.tsu.hits.help.Hashed;
import ru.tsu.hits.repository.CommentRepository;
import ru.tsu.hits.repository.ProjectRepository;
import ru.tsu.hits.repository.TaskRepository;
import ru.tsu.hits.repository.UserRepository;

import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CSVService {



    private  final TaskRepository taskRepository;

    private  final ProjectRepository projectRepository;

    private  final UserRepository userRepository;

    private final CommentRepository commentRepository;


    public void CSVMainLoad(){
        createUserFromCSV();
        createProjectsFromCSV();
        createTasksFromCsv();
        createCommentFromCsv();
    }

    private  void createProjectsFromCSV(){
        var csvStream = getClass()
                .getClassLoader().getResourceAsStream("projects.csv");
        var projects = new CsvToBeanBuilder<ProjectCsv>(new InputStreamReader(Objects.requireNonNull(csvStream)))
                .withSeparator(',')
                .withType(ProjectCsv.class)
                .withSkipLines(1)
                .build()
                .parse();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        for (var project : projects){
            Date createDate = null;
            Date editDate = null;
            try {
                createDate = formatter.parse(project.getCreateDate());
                editDate = formatter.parse(project.getEditDate());
            }catch (ParseException e){
                System.out.println(e.getMessage());
                return;
            }

            var entity = new ProjectEntity(
                    UUID.randomUUID().toString(),
                    createDate,
                    editDate,
                    project.getName(),
                    project.getDescription(),
                    new ArrayList<>()
            );
            projectRepository.save(entity);
        }
    }

    private void createTasksFromCsv(){
        var csvStream = getClass()
                .getClassLoader().getResourceAsStream("tasks.csv");
        var tasks = new CsvToBeanBuilder<TaskCsv>(new InputStreamReader(Objects.requireNonNull(csvStream)))
                .withSeparator(',')
                .withType(TaskCsv.class)
                .withSkipLines(1)
                .build()
                .parse();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        for (var task : tasks){
            var creator = userRepository.findById(task.getCreator())
                    .orElseThrow(()-> new NotFoundException("Big Epic Fail"));
            var executor = userRepository.findById(task.getExecuter())
                    .orElseThrow(()-> new NotFoundException("Big Epic Fail"));
            var project = projectRepository.findById(task.getProject())
                    .orElseThrow(()-> new NotFoundException("Big Epic Fail"));
            SimpleDateFormat formatter2 = new SimpleDateFormat("hh-mm-ss");
            Date createDate = null;
            Date editDate = null;
            Date time = null;
            try {
                createDate = formatter.parse(task.getCreateDate());
                editDate = formatter.parse(task.getEditDate());

            }catch (ParseException e){
                System.out.println(e.getMessage());
                return;
            }

            var entity = new TaskEntity(
                    UUID.randomUUID().toString(),
                    createDate,
                    editDate,
                    task.getName(),
                    task.getDescription(),
                    creator,
                    executor,
                    task.getPriority(),
                    task.getMark(),
                    project,
                    new ArrayList<>()
            );
            taskRepository.save(entity);
        }
    }
    private  void createCommentFromCsv(){
        var csvStream = getClass()
                .getClassLoader().getResourceAsStream("comments.csv");
        var comments = new CsvToBeanBuilder<CommentCsv>(new InputStreamReader(Objects.requireNonNull(csvStream)))
                .withSeparator(',')
                .withType(CommentCsv.class)
                .withSkipLines(1)
                .build()
                .parse();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        for (var comment : comments){
            Date createDate = null;
            Date editDate = null;
            try {
                createDate = formatter.parse(comment.getCreateDate());
                editDate = formatter.parse(comment.getEditDate());
            }catch (ParseException e){
                System.out.println(e.getMessage());
                return;
            }

            var tasks = comment.getTask().split(":");
            var result = new ArrayList<TaskEntity>();
            for(var taskId: tasks){
                System.out.println(taskId);
                var tmp = taskRepository.findById(taskId)
                        .orElseThrow(()-> new NotFoundException("Миша все ...... давай по новой"));
                result.add(tmp);
            }
            var user = userRepository.findById(comment.getUser())
                    .orElseThrow(()-> new NotFoundException("Миша все ...... давай по новой"));
            var entity = new CommentEntity(
                    UUID.randomUUID().toString(),
                    createDate,
                    editDate,
                    user,
                    result,
                    comment.getCommentText()
            );
            commentRepository.save(entity);
        }
    }

    private void createUserFromCSV(){
        var csvStream = getClass()
                .getClassLoader().getResourceAsStream("users.csv");
        var users = new CsvToBeanBuilder<UserCsv>(new InputStreamReader(Objects.requireNonNull(csvStream)))
                .withSeparator(',')
                .withType(UserCsv.class)
                .withSkipLines(1)
                .build()
                .parse();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        for (var user : users){
            Date createDate = null;
            Date editDate = null;
            try {
                createDate = formatter.parse(user.getCreateDate());
                editDate = formatter.parse(user.getEditDate());
            }catch (ParseException e){
                System.out.println(e.getMessage());
                return;
            }
            var hashed = new Hashed();
            var entity = new UserEntity(
                    UUID.randomUUID().toString(),
                    createDate,
                    editDate,
                    user.getSno(),
                    hashed.getHashedPassword(user.getPassword()),
                    user.getUsername(),
                    user.getRole()
            );
            userRepository.save(entity);
        }
    }


}
