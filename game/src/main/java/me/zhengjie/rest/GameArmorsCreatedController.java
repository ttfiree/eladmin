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
import me.zhengjie.domain.GameArmorsCreated;
import me.zhengjie.service.GameArmorsCreatedService;
import me.zhengjie.service.dto.GameArmorsCreatedQueryCriteria;
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
@Api(tags = "armorsCreated管理")
@RequestMapping("/api/gameArmorsCreated")
public class GameArmorsCreatedController {

    private final GameArmorsCreatedService gameArmorsCreatedService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameArmorsCreated:list')")
    public void exportGameArmorsCreated(HttpServletResponse response, GameArmorsCreatedQueryCriteria criteria) throws IOException {
        gameArmorsCreatedService.download(gameArmorsCreatedService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询armorsCreated")
    @ApiOperation("查询armorsCreated")
    @PreAuthorize("@el.check('gameArmorsCreated:list')")
    public ResponseEntity<Object> queryGameArmorsCreated(GameArmorsCreatedQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameArmorsCreatedService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增armorsCreated")
    @ApiOperation("新增armorsCreated")
    @PreAuthorize("@el.check('gameArmorsCreated:add')")
    public ResponseEntity<Object> createGameArmorsCreated(@Validated @RequestBody GameArmorsCreated resources){
        return new ResponseEntity<>(gameArmorsCreatedService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改armorsCreated")
    @ApiOperation("修改armorsCreated")
    @PreAuthorize("@el.check('gameArmorsCreated:edit')")
    public ResponseEntity<Object> updateGameArmorsCreated(@Validated @RequestBody GameArmorsCreated resources){
        gameArmorsCreatedService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除armorsCreated")
    @ApiOperation("删除armorsCreated")
    @PreAuthorize("@el.check('gameArmorsCreated:del')")
    public ResponseEntity<Object> deleteGameArmorsCreated(@RequestBody Integer[] ids) {
        gameArmorsCreatedService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}