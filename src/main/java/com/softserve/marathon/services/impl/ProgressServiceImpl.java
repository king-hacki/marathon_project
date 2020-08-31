package com.softserve.marathon.services.impl;

import com.softserve.marathon.exceptions.ProgressNotFoundByIdException;
import com.softserve.marathon.model.Progress;
import com.softserve.marathon.model.User;
import com.softserve.marathon.model.Task;
import com.softserve.marathon.model.enums.TaskStatus;
import com.softserve.marathon.repositories.ProgressRepository;
import com.softserve.marathon.services.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProgressServiceImpl implements ProgressService {

    private ProgressRepository progressRepository;

    @Autowired
    public ProgressServiceImpl(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    @Override
    public Progress getProgressById(long id) {
        return progressRepository.findById(id)
                .orElseThrow(ProgressNotFoundByIdException::new);
    }

    @Override
    public Progress addTaskForStudent(Task task, User user) {
        Progress progress = new Progress();
        progress.setTask(task);
        progress.setUser(user);
        Progress progressEntity = progressRepository.save(progress);
        task.getProgresses().add(progressEntity);
        user.getProgresses().add(progressEntity);
        return progressEntity;
    }

    @Override
    public Progress addOrUpdateProgress(Progress progress) {
        return progressRepository.save(progress);
    }

    @Override
    public List<Progress> allProgressesByUserAndMarathonId(long userId, long marathonId) {
        return progressRepository.findAllByUserIdAndTaskSprintMarathonId(userId, marathonId);
    }

    @Override
    public List<Progress> allProgressesByUserAndSprintId(long userId, long sprintId) {
        return progressRepository.findAllByUserIdAndTaskSprintId(userId, sprintId);
    }
}
