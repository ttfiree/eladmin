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
import me.zhengjie.domain.GameMonster;
import me.zhengjie.service.GameMonsterService;
import me.zhengjie.service.dto.GameMonsterQueryCriteria;
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
@Api(tags = "monster管理")
@RequestMapping("/api/gameMonster")
public class GameMonsterController {

    private final GameMonsterService gameMonsterService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameMonster:list')")
    public void exportGameMonster(HttpServletResponse response, GameMonsterQueryCriteria criteria) throws IOException {
        gameMonsterService.download(gameMonsterService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询monster")
    @ApiOperation("查询monster")
    @PreAuthorize("@el.check('gameMonster:list')")
    public ResponseEntity<Object> queryGameMonster(GameMonsterQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameMonsterService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增monster")
    @ApiOperation("新增monster")
    @PreAuthorize("@el.check('gameMonster:add')")
    public ResponseEntity<Object> createGameMonster(@Validated @RequestBody GameMonster resources){
        return new ResponseEntity<>(gameMonsterService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改monster")
    @ApiOperation("修改monster")
    @PreAuthorize("@el.check('gameMonster:edit')")
    public ResponseEntity<Object> updateGameMonster(@Validated @RequestBody GameMonster resources){
        gameMonsterService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除monster")
    @ApiOperation("删除monster")
    @PreAuthorize("@el.check('gameMonster:del')")
    public ResponseEntity<Object> deleteGameMonster(@RequestBody Integer[] ids) {
        gameMonsterService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}