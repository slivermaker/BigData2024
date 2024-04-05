import requests
from bs4 import BeautifulSoup
import os
import time
import random


def get_response(url):
    try:
        # 添加头部信息
        headers = {
            "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36",
            "Accept-Language": "en-US,en;q=0.9",  # 设置接受的语言类型
        }
        response = requests.get(url, headers=headers, timeout=10)
        # 进行状态码判断，是否正确读取到网页
        if response.status_code == 200:
            return response
        return None
    except requests.RequestException:
        return None


def get_html(response):
    html = BeautifulSoup(response.text, "lxml")
    return html


def get_movies(html):
    # 电影名称，tmdb_id，海报链接
    movies_tag = html.find_all("a", class_="image")
    movies = []  # 初始化存放电影信息的列表
    for i in movies_tag:
        movie = {}  # 此处需要使用深拷贝，因此字典初始化在循环内
        movie["movie_title"] = i["title"]
        # 获取封面图片链接
        if i.img:
            poster_url = i.img["src"]
            # 修正链接格式
            if poster_url.startswith("//"):
                poster_url = "https:" + poster_url
            movie["movie_poster_url"] = poster_url
            movies.append(movie)
    return movies


def save_movie_info(movie_info, filename="name", path=""):
    with open(os.path.join(path, filename + ".txt"), "a+", encoding="utf-8") as f:
        f.write(movie_info + "\n")


def save_movie_poster_urls(movie_poster_urls, filename="picture", path=""):
    with open(os.path.join(path, filename + ".txt"), "a+", encoding="utf-8") as f:
        for url in movie_poster_urls:
            f.write(url + "\n")


def run():
    for i in range(200, 300):
        url = "https://www.themoviedb.org/movie" + "?page=" + str(i)
        response = get_response(url)  # 获取网页，拿到response
        if response is None:
            print(f"Failed to fetch page {i}, skipping...")
            continue
        html = get_html(response)  # 解析网页，拿到html
        movies = get_movies(html)  # 匹配数据，拿到电影数据
        movie_titles = [movie["movie_title"] for movie in movies]
        movie_poster_urls = [movie["movie_poster_url"] for movie in movies]
        save_movie_info("\n".join(movie_titles))  # 存储电影名称
        save_movie_poster_urls(movie_poster_urls)  # 存储电影海报链接
        print(f"Page {i} saved.")
        # 添加随机延时，避免被网站识别为爬虫
        time.sleep(random.uniform(1, 3))


if __name__ == "__main__":
    run()
