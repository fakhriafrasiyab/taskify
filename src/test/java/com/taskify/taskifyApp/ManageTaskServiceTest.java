package com.taskify.taskifyApp;

import com.taskify.taskifyApp.entity.Status;
import com.taskify.taskifyApp.payload.request.TaskStoreRequest;
import com.taskify.taskifyApp.security.services.UserDetailsImpl;
import com.taskify.taskifyApp.service.ManageTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageTaskServiceTest {

    @Autowired
    private ManageTaskService manageTaskService;

    @Test
    public void test_createAndAssignTask() {
        LocalDateTime now = LocalDateTime.now();
        List<Integer> userIdList = new ArrayList<>();
        userIdList.add(1);
        UserDetailsImpl user = new UserDetailsImpl(1, "fakhriafrasiyab@gmail.com", "test1234", null);
        manageTaskService.createAndAssignTask("Test yeni task", "test description",
                now, Status.OPEN, userIdList,user.getEmail());
    }

    @Test
    public void test_getList() {

    }
}
