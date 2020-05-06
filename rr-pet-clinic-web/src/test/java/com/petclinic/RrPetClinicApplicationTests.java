package com.petclinic;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@RunWith(SpringRunner.class)  //old.. replaced to @ExtendWith in junit 5
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RrPetClinicApplicationTests {

    @Test
    public void contextLoads() {
    }

}
