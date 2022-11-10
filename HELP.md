# 数据解析小工具

- 支持JSON、XML格式
- 解析配置
- 任务配置

## 解析数据 -> HTML

<br/>

### 解析规则配置

- 样式数据
  ```
  basketball{
    "company": "家里蹲",
    "tag": "有格局",
    "userInfo": {
      "age": 18,
      "name": "张张",
      "tag": "大一萌新",
      "weight": 60,
      "unit": "kg"
    },
    "hobby": {
      "games": [
        "Dota",
        "Csgo",
        "LOL"
      ],
      "sports": [
        {
          "name": "羽毛球",
          "english": "badminton"
        },
        {
          "name": "篮球",
          "english": "basketball"
        }
      ]
    }
  }
  ```

  - 配置

    |expression|结果|
    | :----:  | :----:  |
    |company|家里蹲		|
    |{userInfo}.name|张张|
    |{userInfo}.weight&unit|60kg|
    |{userInfo}.name;tag;{userInfo}.weight&unit|张张 ; 有格局 ;  60kg |
    |{hobby}.[games].()|Dota、Csgo、LOL|
    |{hobby}.[games].(0,2)|Dota、LOL|
    |{hobby}.[sports].name|羽毛球、篮球|
    |{hobby}.[sports].name&english|羽毛球-badminton、篮球-basketball|
    |{userInfo}.name;{hobby}.[sports].name&english|张张、羽毛球-badminton、篮球-basketball|


<br/>

### 任务配置

- 任务更新数据库配置
  ```
  read_sql： SELECT id,test_xml FROM `test3` 
  read_col： 1，2     // 1代表id 2代表test_xml
  read_parse_col: 2   // 代表要解析的列为 2 
  read_incremental： id //可不配；配置代表增量字段为id
  read_incremental_max： 500 //可不配；配置代表增量字段条件为 >500
  
  dml_sql: update `test3` set test_xml=?,origin=? where id=?
  dml_col: ?;2;1      // ?代表解析的html数据 2代表test_xml 1 代表id
  dml_thread_num： 3  // 代表3个线程进行写入 
  ```

- 调度配置
  ```
  cron: cron表达式
  task_switch： 开启状态
  ```