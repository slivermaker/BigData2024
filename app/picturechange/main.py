import random
import time
import pymysql
import requests
import json
from urllib.parse import quote
from fake_useragent import UserAgent


# 创建一个 UserAgent 对象
ua = UserAgent()

# 连接数据库
conn = pymysql.connect(
    host="localhost",
    user="root",
    password="yyz999",
    database="movierecommend",
    charset="utf8mb4",
    cursorclass=pymysql.cursors.DictCursor,
)

# 创建游标对象
cursor = conn.cursor()

# 查询数据库中的电影名称
query = "SELECT movieid, moviename,picture FROM movieinfo"
cursor.execute(query)
movies = cursor.fetchall()

# 循环遍历每个电影名称，并爬取更新结果
for i, movie in enumerate(movies):
    if movie["movieid"] < 2930 or "qnmob3" in movie["picture"]:
        continue
    # time.sleep(random.uniform(8.4, 12.4))  # 随机等待时间是1秒和3秒之间的一个小数
    print(movie["movieid"])
    movie_id = movie["movieid"]
    movie_name = movie["moviename"]
    word = quote(movie_name)
    url = "https://m.douban.com/rexxar/api/v2/search?q=" + word

    # 随机选择一个用户代理
    user_agent = ua.random
    headers = {
        "User-Agent": user_agent,
        "Cookie": 'gr_user_id=fdb73cc0-fc7c-4d60-985b-37ab95a27023; ll="118276"; bid=NrunAJduIaE; _pk_id.100001.8cb4=4c68efb87d78c2ba.1691981945.; __yadk_uid=zbS41td7KwOxLg5ZD6LPqc1PhAhdagRi; viewed="36142067_35926998_34917990_34782257_33437381_34998671_36104107_26317662_35863224_6047742"; ap_v=0,6.0',
        "Referer": "https://www.douban.com/search?q=" + word,
    }

    # 发送请求获取数据
    try:
        response = requests.get(url=url, headers=headers)
        response.encoding = "utf-8"
        res = json.loads(response.text).get("subjects", {}).get("items", [])
        if res:
            cover_url = res[0]["target"]["cover_url"]

            # 更新数据库中的图片链接
            update_query = "UPDATE movieinfo SET picture = %s WHERE movieid = %s"
            cursor.execute(update_query, (cover_url, movie_id))
            conn.commit()
            print(movie["picture"])
    except KeyError:
        # 如果没有 'subjects' 键，将电影的 ID 和名称写入到 no.txt 文件中
        with open("no.txt", "a", encoding="utf-8") as f:
            f.write(f"Movie ID: {movie_id}, Movie Name: {movie_name}\n")
        continue
    except Exception as e:
        print(f"An error occurred for movie: {movie_name}, Error: {e}")

# 关闭游标和连接
cursor.close()
conn.close()
