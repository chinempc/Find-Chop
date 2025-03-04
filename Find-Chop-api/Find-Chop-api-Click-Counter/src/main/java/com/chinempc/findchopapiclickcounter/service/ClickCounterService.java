package com.chinempc.findchopapiclickcounter.service;

import com.chinempc.findchopapiclickcounter.dto.RequestDTO;
import com.chinempc.findchopapiclickcounter.dto.ResponseDTO;
import com.chinempc.findchopapiclickcounter.mapper.ClickCounterMapper;
import com.chinempc.findchopapiclickcounter.model.Click;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClickCounterService {

    private final MongoTemplate mongoTemplate;
    private final ClickCounterMapper mapper;

    public Boolean updateClickCounterByName(RequestDTO request) {
        Query query = new Query();
        query.addCriteria(Criteria.where("recipe_name").is(request.getRecipe_name()));
        Update update = new Update().inc("click_count", 1);

        Click updatedClick = mongoTemplate.findAndModify(query,
                update,
                FindAndModifyOptions.options().returnNew(true),
                Click.class);

        if (updatedClick == null) {
            throw new IllegalStateException("Recipe not found.");
        }

        log.info("Updated Click Count for {}", request.getRecipe_name());
        return true;
    }

    public List<ResponseDTO> getTopThreeRecipes() {
        // Get list of 3 Recipe names sorted by click count
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "click_count"));
        query.limit(3);

        return mapper.multiToDTO(mongoTemplate.find(query, Click.class));
    }

    public List<ResponseDTO> CalculateTopRecipes() {
        /*
        THIS IS ASYNC
            Consuming messages from the rabbit queue should be automatic, thus no need to check
            When a new read is done, the db will update, this is not an urgent feature so being as UTD isn't needed
            This function will simply make a call to the databse to get the top 3 recipe names

            Call getTopRecipes to get top recipes
            Consume Top-Recipes topic
            Compare the names from bullet points 1 & 2
                - Same?
                    Do Nothing, no need to update
                - Different?
                    Send out the new names to RabbitMQ
         */
    }
}
