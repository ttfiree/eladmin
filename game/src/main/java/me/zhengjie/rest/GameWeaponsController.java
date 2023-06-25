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
import me.zhengjie.domain.GameWeapons;
import me.zhengjie.service.GameWeaponsService;
import me.zhengjie.service.dto.GameWeaponsQueryCriteria;
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
@Api(tags = "weaspons管理")
@RequestMapping("/api/gameWeapons")
public class GameWeaponsController {

    private final GameWeaponsService gameWeaponsService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameWeapons:list')")
    public void exportGameWeapons(HttpServletResponse response, GameWeaponsQueryCriteria criteria) throws IOException {
        gameWeaponsService.download(gameWeaponsService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询weaspons")
    @ApiOperation("查询weaspons")
    @PreAuthorize("@el.check('gameWeapons:list')")
    public ResponseEntity<Object> queryGameWeapons(GameWeaponsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameWeaponsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增weaspons")
    @ApiOperation("新增weaspons")
    @PreAuthorize("@el.check('gameWeapons:add')")
    public ResponseEntity<Object> createGameWeapons(@Validated @RequestBody GameWeapons resources){
        return new ResponseEntity<>(gameWeaponsService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改weaspons")
    @ApiOperation("修改weaspons")
    @PreAuthorize("@el.check('gameWeapons:edit')")
    public ResponseEntity<Object> updateGameWeapons(@Validated @RequestBody GameWeapons resources){
        gameWeaponsService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除weaspons")
    @ApiOperation("删除weaspons")
    @PreAuthorize("@el.check('gameWeapons:del')")
    public ResponseEntity<Object> deleteGameWeapons(@RequestBody Integer[] ids) {
        gameWeaponsService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}