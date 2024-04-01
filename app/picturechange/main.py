import requests
from bs4 import BeautifulSoup


def get_movie_cover(movie_name):
    # 设置请求头部信息
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36"
    }

    # 构造TMDB电影搜索的URL
    search_url = f"https://www.themoviedb.org/search?query={movie_name}"

    # 发送HTTP请求
    response = requests.get(search_url, headers=headers)
    response.raise_for_status()  # 检查请求是否成功

    # 使用 BeautifulSoup 解析HTML内容
    soup = BeautifulSoup(response.text, "html.parser")

    # 获取搜索结果中第一个电影的链接
    movie_link = soup.select_one(".result a")["href"]

    # 访问电影详情页面
    movie_response = requests.get(
        "https://www.themoviedb.org" + movie_link, headers=headers
    )
    movie_response.raise_for_status()  # 检查请求是否成功

    # 使用 BeautifulSoup 解析电影详情页面的HTML内容
    movie_soup = BeautifulSoup(movie_response.text, "html.parser")

    # 获取电影封面图片链接
    cover_link = movie_soup.find("img", class_="poster")["src"]

    return cover_link


# 测试获取电影封面图片链接
movie_name = "Toy Story"
cover_link = get_movie_cover(movie_name)
print("电影封面链接:", cover_link)
