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
import me.zhengjie.domain.GameCharacter;
import me.zhengjie.service.GameCharacterService;
import me.zhengjie.service.dto.GameCharacterQueryCriteria;
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
* @date 2023-06-12
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "character管理")
@RequestMapping("/api/gameCharacter")
public class GameCharacterController {

    private final GameCharacterService gameCharacterService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameCharacter:list')")
    public void exportGameCharacter(HttpServletResponse response, GameCharacterQueryCriteria criteria) throws IOException {
        gameCharacterService.download(gameCharacterService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询character")
    @ApiOperation("查询character")
    @PreAuthorize("@el.check('gameCharacter:list')")
    public ResponseEntity<Object> queryGameCharacter(GameCharacterQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameCharacterService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增character")
    @ApiOperation("新增character")
    @PreAuthorize("@el.check('gameCharacter:add')")
    public ResponseEntity<Object> createGameCharacter(@Validated @RequestBody GameCharacter resources){
        return new ResponseEntity<>(gameCharacterService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改character")
    @ApiOperation("修改character")
    @PreAuthorize("@el.check('gameCharacter:edit')")
    public ResponseEntity<Object> updateGameCharacter(@Validated @RequestBody GameCharacter resources){
        gameCharacterService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除character")
    @ApiOperation("删除character")
    @PreAuthorize("@el.check('gameCharacter:del')")
    public ResponseEntity<Object> deleteGameCharacter(@RequestBody Integer[] ids) {
        gameCharacterService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}