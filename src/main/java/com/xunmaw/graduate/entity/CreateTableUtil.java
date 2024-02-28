package com.xunmaw.graduate.entity;

import java.lang.reflect.Field;

/**
 * @Author ocean
 * @data 2022/9/8
 * @description 创建数据库表工具类
 */
public class CreateTableUtil {

    /**
     * Java实体转mysql建表
     *
     * @param clazz        需要转的实体对象
     * @param iscreateTime 是否需要生成创建人，创建时间等信息
     * @param <T>
     * @return
     */
    public static <T> String createTableSql(Class<T> clazz, boolean iscreateTime) {
        //得到APIModel注解的value
        String tableName = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);
        Field[] fields = clazz.getDeclaredFields();
        String param = null;
        String cameCaseColumn = null;
        String underScoreCaseColumn = null;
        StringBuilder sql = new StringBuilder();
        //以下生成建表Sql
        sql.append("create table ").append(tableName.toLowerCase()).append("(\n");
        // sql.append("id VARCHAR(32) not NULL COMMENT '主键id',\n");
        String idKey = null;
        int index = 0;
        for (Field f : fields) {
            cameCaseColumn = f.getName();
            underScoreCaseColumn = cameCaseColumn;
            for (int i = 0; i < cameCaseColumn.length(); i++) {
                if (Character.isUpperCase(cameCaseColumn.charAt(i))) {
                    // 将javabean中小驼峰命名变量的“大写字母”转换为“_小写字母”
                    underScoreCaseColumn = cameCaseColumn.substring(0, i) + '_' + cameCaseColumn.substring(i, i + 1).toLowerCase() + cameCaseColumn.substring(i + 1, cameCaseColumn.length());
                }
            }
            param = f.getType().getTypeName();
            //得到ApiModelProperty 注解的value
            switch (param) {
                case "java.lang.Integer":
                    sql.append(underScoreCaseColumn).append(" ");
                    sql.append("int(8) "+(index!=0?"DEFAULT NULL":"NOT NULL")+ " COMMENT '" + "',\n");
                    break;
                case "java.lang.Boolean":
                    sql.append(underScoreCaseColumn).append(" ");
                    sql.append("tinyint(1) "+(index!=0?"DEFAULT NULL":"NOT NULL")+ " COMMENT '"  + "',\n");
                    break;
                case "java.math.BigDecimal":
                    sql.append(underScoreCaseColumn).append(" ");
                    sql.append("decimal(18,2) "+(index!=0?"DEFAULT NULL":"NOT NULL")+ " COMMENT '"  + "',\n");
                    break;
                case "java.util.Date":
                    sql.append(underScoreCaseColumn).append(" ");
                    sql.append("datetime "+(index!=0?"DEFAULT NULL":"NOT NULL")+ " COMMENT '"  + "',\n");
                    break;
                default:
                    sql.append(underScoreCaseColumn).append(" ");
                    sql.append("VARCHAR(32) "+(index!=0?"DEFAULT NULL":"NOT NULL")+ " COMMENT '" + "',\n");
            }
            if (index == 0) {
                idKey = underScoreCaseColumn;
            }
            index = index+1;
        }
        if (iscreateTime) {
            sql.append("sys_deleted tinyint(1) DEFAULT '0' COMMENT '删除状态，1删除 0正常',\n");
            sql.append("sys_created datetime NOT NULL COMMENT '创建时间',\n");
            sql.append("sys_created_by varchar(32) NOT NULL COMMENT '创建人',\n");
            sql.append("sys_modified datetime NOT NULL COMMENT '更新时间',\n");
            sql.append("sys_modified_by varchar(32) NOT NULL COMMENT '更新人',\n");
        }
        sql.append("PRIMARY KEY ("+idKey+")");
        //sql.delete(sql.lastIndexOf(","), sql.length());
        sql.append("\n)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='" + "';");
    
        System.out.println(sql);
        return sql.toString();
    }

    public static void main(String[] args) {
        createTableSql(Depart.class, false);
        createTableSql(Enterprise.class, false);
        createTableSql(EnterpriseType.class, false);
        createTableSql(GraduatePlace.class, false);
        createTableSql(Intention.class, false);
        createTableSql(Item.class, false);
        createTableSql(Major.class, false);
        createTableSql(Manager.class, false);
        createTableSql(NoteState.class, false);
        createTableSql(Obtain.class, false);
        createTableSql(ObtainState.class, false);
        createTableSql(Political.class, false);
        createTableSql(Province.class, false);
        createTableSql(Region.class, false);
        createTableSql(Requirement.class, false);
        createTableSql(RequireNote.class, false);
        createTableSql(Student.class, false);
    }


}