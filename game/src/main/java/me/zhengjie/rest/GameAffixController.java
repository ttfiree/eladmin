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
import me.zhengjie.domain.GameAffix;
import me.zhengjie.service.GameAffixService;
import me.zhengjie.service.dto.GameAffixQueryCriteria;
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
@Api(tags = "affix管理")
@RequestMapping("/api/gameAffix")
public class GameAffixController {

    private final GameAffixService gameAffixService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameAffix:list')")
    public void exportGameAffix(HttpServletResponse response, GameAffixQueryCriteria criteria) throws IOException {
        gameAffixService.download(gameAffixService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询affix")
    @ApiOperation("查询affix")
    @PreAuthorize("@el.check('gameAffix:list')")
    public ResponseEntity<Object> queryGameAffix(GameAffixQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameAffixService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增affix")
    @ApiOperation("新增affix")
    @PreAuthorize("@el.check('gameAffix:add')")
    public ResponseEntity<Object> createGameAffix(@Validated @RequestBody GameAffix resources){
        return new ResponseEntity<>(gameAffixService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改affix")
    @ApiOperation("修改affix")
    @PreAuthorize("@el.check('gameAffix:edit')")
    public ResponseEntity<Object> updateGameAffix(@Validated @RequestBody GameAffix resources){
        gameAffixService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除affix")
    @ApiOperation("删除affix")
    @PreAuthorize("@el.check('gameAffix:del')")
    public ResponseEntity<Object> deleteGameAffix(@RequestBody Integer[] ids) {
        gameAffixService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}