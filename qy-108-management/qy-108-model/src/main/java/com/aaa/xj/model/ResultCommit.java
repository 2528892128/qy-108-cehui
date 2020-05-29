package com.aaa.xj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_result_commit")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResultCommit implements Serializable {

    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 成果名称
     */
    private String name;

    /**
     * 介质类型
     */
    @Column(name = "medium_type")
    private String mediumType;

    /**
     * 成果日期
     */
    @Column(name = "result_date")
    private String resultDate;

    /**
     * 关联项目编号
     */
    @Column(name = "ref_id")
    private Long refId;

    /**
     * 比例尺
     */
    @Column(name = "plotting_scale")
    private String plottingScale;

    /**
     * 新图号
     */
    @Column(name = "new_figure")
    private String newFigure;

    /**
     * 旧图号
     */
    @Column(name = "old_figure")
    private String oldFigure;

    /**
     * 图名
     */
    private String figure;

    /**
     * 数据格式
     */
    @Column(name = "data_format")
    private String dataFormat;

    /**
     * 生产日期
     */
    @Column(name = "create_date")
    private String createDate;

    /**
     * 高程基准
     */
    @Column(name = "height_datum")
    private Integer heightDatum;

    /**
     * 备注说明
     */
    private String memo;

    /**
     * 中央子午线
     */
    private String meridian;

    /**
     * 坐标系
     */
    private String coordinate;

    private MappingProject mappingProject;

}