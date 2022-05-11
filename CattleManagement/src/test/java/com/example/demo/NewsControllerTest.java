package com.example.demo;

import com.example.demo.controller.NewsController;
import com.example.demo.model.Employee;
import com.example.demo.model.Entities;
import com.example.demo.model.News;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NewsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    NewsController newsController;
    @Test
    public void getListSNews_1() {
        ResponseEntity<Page<News>> responseEntity
                = this.newsController.findAllNews(PageRequest.of(0, 6), "", "");

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(4, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(20, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("1",
                responseEntity.getBody().getContent().get(1).getNewsId());
        Assertions.assertEquals("Sửa đổi, bổ sung về xử phạt trong lĩnh vực thú y…",
                responseEntity.getBody().getContent().get(1).getNewsName());
        Assertions.assertEquals("Ngày 11/01/2022, Chính phủ ban hành Nghị định 07/2022/NĐ-CP về việc sửa đổi, bổ sung một số điều của các Nghị định về xử phạt vi phạm hành chính trong lĩnh vực lâm nghiệp; bảo vệ và kiểm dịch thực vật; thú y; chăn nuôi.",
                responseEntity.getBody().getContent().get(1).getDetailDescription());
        Assertions.assertEquals("Ông Paul A. Anderson, Tổng Giám đốc của Genesus Inc cho rằng, thị trường dự kiến sẽ trải qua thời kỳ khó khăn phía trước, do dịch tả lợn Châu Phi tiếp tục ảnh hưởng đến các trang trại chăn nuôi lợn ở Thái Lan. Mọi người đều biết rằng giá lao dốc chỉ là tạm thời. Tình trạng thiệt hại hàng loạt lợn nái và lợn thương phẩm do Dịch tả ASF vẫn chưa được giải quyết và thậm chí ngày càng gia tăng do hầu hết các trang trại bị dịch bệnh ASF vẫn phải cung cấp sản phẩm.",
                responseEntity.getBody().getContent().get(1).getContent());
//        Assertions.assertEquals(2022-04-22,
//                responseEntity.getBody().getContent().get(1).getCreatedDate());
        Assertions.assertEquals("1",
                responseEntity.getBody().getContent().get(1).getHighlight());
        Assertions.assertEquals("https://firebasestorage.googleapis.com/v0/b/sprint-affd0.appspot.com/o/AdobeStock_287230388.jpeg?alt=media&token=5fa351ac-81b5-4f94-9356-63cd84c50b03",
                responseEntity.getBody().getContent().get(1).getImage());
        Assertions.assertEquals(false,
                responseEntity.getBody().getContent().get(1).getDelete());
        Assertions.assertEquals(323,
                responseEntity.getBody().getContent().get(1).getTotalView());
        Assertions.assertEquals("Luật Chăn Nuôi",
                responseEntity.getBody().getContent().get(1).getType());

    }

    @Test
    public void getListSNews_2() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/public/news"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    public void getListSNews_3() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/public/news", "1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
//    @Test
//    public void getInfoStudent_3() throws Exception {
//        this.mockMvc.perform(
//                MockMvcRequestBuilders.get("/api/public/news?search=","viet"))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }

//    @Test
//    public void getInfoStudent_4() throws Exception {
//        this.mockMvc.perform(
//                MockMvcRequestBuilders.get("/employee/list",""))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    @Test
//    public void getInfoStudent_5() throws Exception {
//        this.mockMvc.perform(
//                MockMvcRequestBuilders.get("/employee/list","Hoa An"))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    @Test
//    public void getInfoStudent_6() throws Exception {
//        this.mockMvc.perform(
//                MockMvcRequestBuilders.get("/employee/list","Phạm Lê Khanh"))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    @Test
//    public void getInfoStudent_7() throws Exception {
//        this.mockMvc.perform(
//                MockMvcRequestBuilders.get("/employee/list","Trần Thuỳ My"))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$.employeeId").value("N02"))
//                .andExpect(jsonPath("$.email").value("my@gmail.com"))
//                .andExpect(jsonPath("$.birthDay").value("1999-12-30"))
//                .andExpect(jsonPath("$.gender").value(false));
//    }

}
