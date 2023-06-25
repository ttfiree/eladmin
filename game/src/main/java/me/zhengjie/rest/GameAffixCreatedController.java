/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.domain.GameAffixCreated;
import me.zhengjie.service.GameAffixCreatedService;
import me.zhengjie.service.dto.GameAffixCreatedQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://eladmin.vip
* @author lyc
* @date 2023-06-22
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "affixCreated管理")
@RequestMapping("/api/gameAffixCreated")
public class GameAffixCreatedController {

    private final GameAffixCreatedService gameAffixCreatedService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameAffixCreated:list')")
    public void exportGameAffixCreated(HttpServletResponse response, GameAffixCreatedQueryCriteria criteria) throws IOException {
        gameAffixCreatedService.download(gameAffixCreatedService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询affixCreated")
    @ApiOperation("查询affixCreated")
    @PreAuthorize("@el.check('gameAffixCreated:list')")
    public ResponseEntity<Object> queryGameAffixCreated(GameAffixCreatedQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameAffixCreatedService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增affixCreated")
    @ApiOperation("新增affixCreated")
    @PreAuthorize("@el.check('gameAffixCreated:add')")
    public ResponseEntity<Object> createGameAffixCreated(@Validated @RequestBody GameAffixCreated resources){
        return new ResponseEntity<>(gameAffixCreatedService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改affixCreated")
    @ApiOperation("修改affixCreated")
    @PreAuthorize("@el.check('gameAffixCreated:edit')")
    public ResponseEntity<Object> updateGameAffixCreated(@Validated @RequestBody GameAffixCreated resources){
        gameAffixCreatedService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除affixCreated")
    @ApiOperation("删除affixCreated")
    @PreAuthorize("@el.check('gameAffixCreated:del')")
    public ResponseEntity<Object> deleteGameAffixCreated(@RequestBody Integer[] ids) {
        gameAffixCreatedService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}