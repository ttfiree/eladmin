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
import me.zhengjie.domain.GameArmors;
import me.zhengjie.service.GameArmorsService;
import me.zhengjie.service.dto.GameArmorsQueryCriteria;
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
@Api(tags = "armors管理")
@RequestMapping("/api/gameArmors")
public class GameArmorsController {

    private final GameArmorsService gameArmorsService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameArmors:list')")
    public void exportGameArmors(HttpServletResponse response, GameArmorsQueryCriteria criteria) throws IOException {
        gameArmorsService.download(gameArmorsService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询armors")
    @ApiOperation("查询armors")
    @PreAuthorize("@el.check('gameArmors:list')")
    public ResponseEntity<Object> queryGameArmors(GameArmorsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameArmorsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增armors")
    @ApiOperation("新增armors")
    @PreAuthorize("@el.check('gameArmors:add')")
    public ResponseEntity<Object> createGameArmors(@Validated @RequestBody GameArmors resources){
        return new ResponseEntity<>(gameArmorsService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改armors")
    @ApiOperation("修改armors")
    @PreAuthorize("@el.check('gameArmors:edit')")
    public ResponseEntity<Object> updateGameArmors(@Validated @RequestBody GameArmors resources){
        gameArmorsService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除armors")
    @ApiOperation("删除armors")
    @PreAuthorize("@el.check('gameArmors:del')")
    public ResponseEntity<Object> deleteGameArmors(@RequestBody Integer[] ids) {
        gameArmorsService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}