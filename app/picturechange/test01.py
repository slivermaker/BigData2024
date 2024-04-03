import requests
import json
from urllib.parse import quote
import random

# 用户代理列表，可以自行扩展
user_agents = [
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.192 Safari/537.36",
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36",
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36",
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
]

word = quote("The American President")
url = "https://m.douban.com/rexxar/api/v2/search?q=" + word

# 随机选择一个用户代理
user_agent = random.choice(user_agents)
headers = {
    "User-Agent": user_agent,
    "Cookie": 'gr_user_id=fdb73cc0-fc7c-4d60-985b-37ab95a27023; ll="118276"; bid=NrunAJduIaE; _pk_id.100001.8cb4=4c68efb87d78c2ba.1691981945.; __yadk_uid=zbS41td7KwOxLg5ZD6LPqc1PhAhdagRi; viewed="36142067_35926998_34917990_34782257_33437381_34998671_36104107_26317662_35863224_6047742"; ap_v=0,6.0',
    "Referer": "https://www.douban.com/search?q=" + word,
}

response = requests.get(url=url, headers=headers)
response.encoding = "utf-8"
res = json.loads(response.text)["subjects"]["items"]
if res:
    print(res[0]["target"]["cover_url"])
