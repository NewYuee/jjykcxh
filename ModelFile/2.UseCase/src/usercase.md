@startuml

left to right direction
skinparam packageStyle rect
footer NewYue
actor user as u1
actor admin as u2

u2--|>u1

package 新闻模块 {
    usecase 查看新闻 as u11
    usecase 发布新闻 as u12
    usecase 编辑新闻 as u13
    usecase 删除文章 as u14
}

package 评论模块 {
    usecase 发布评论 as u21
    usecase 回复评论 as u22
    usecase 删除评论 as u23
}

package 公告模块 {
    usecase 发布公告 as u31
    usecase 删除公告 as u32
    usecase 编辑公告 as u33
    usecase 查看公告 as u34
}

package 相册模块 {
    usecase 浏览相册 as u41
    usecase 编辑相册 as u42
    usecase 上传图集 as u43
}

u1-->u11
u1-->u21
u1-->u22
u1-->u23
u1-->u34
u1-->u41
u2-->u12
u2-->u13
u2-->u14
u2-->u31
u2-->u32
u2-->u33
u2-->u42
u2-->u43

@enduml