package com.aaa.xj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "t_dept")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Dept implements Serializable {
    /**
     * 部门ID
     */
    @Id
    @Column(name = "DEPT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptId;

    /**
     * 上级部门ID
     */
    @Column(name = "PARENT_ID")
    private Integer parentId;

    /**
     * 部门名称
     */
    @Column(name = "DEPT_NAME")
    private String deptName;

    /**
     * 排序
     */
    @Column(name = "ORDER_NUM")
    private Integer orderNum;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private String createTime;

    /**
     * 修改时间
     */
    @Column(name = "MODIFY_TIME")
    private String modifyTime;

    /**
     * 该部门的子部门信息
     */
    private List<Dept> children;

}