import random
import time
import pymysql
import requests
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
    if "media" in movie["picture"]:
        continue

    # time.sleep(random.uniform(8.4, 12.4))  # 随机等待时间是1秒和3秒之间的一个小数
    print(movie["movieid"])
    movie_id = movie["movieid"]
    movie_name = movie["moviename"]
    url = f"https://api.themoviedb.org/3/search/movie?api_key=YOUR_API_KEY&query={movie_name}"

    # 随机选择一个用户代理
    user_agent = ua.random
    headers = {
        "User-Agent": user_agent,
    }

    # 发送请求获取数据
    try:
        response = requests.get(url=url, headers=headers)
        response.raise_for_status()
        res = response.json()
        if res.get("results"):
            cover_path = res["results"][0]["poster_path"]
            if cover_path:
                # 构建完整的封面图片链接
                cover_url = f"https://image.tmdb.org/t/p/w500{cover_path}"
                # 更新数据库中的图片链接
                update_query = "UPDATE movieinfo SET picture = %s WHERE movieid = %s"
                cursor.execute(update_query, (cover_url, movie_id))
                conn.commit()
                print(f"Cover URL updated for movie: {movie_name}")
    except Exception as e:
        print(f"An error occurred for movie: {movie_name}, Error: {e}")

# 关闭游标和连接
cursor.close()
conn.close()
