import requests, bs4
import sys
import re, json

class Video:
    def __init__(self, videoId, url, titulo):
        self.videoId = videoId
        self.thumbnailUrl = url
        self.titulo = titulo

texto = requests.get("https://www.youtube.com/results?search_query=radiohead").text
soup = bs4.BeautifulSoup(texto, 'lxml')
scp = soup.find_all('script')[-6]
json_texto = re.search('var ytInitialData = (.+)[,;]{1}', str(scp)).group(1)
json_dados = json.loads(json_texto)

conteudo = (
    json_dados['contents']['twoColumnSearchResultsRenderer']
    ['primaryContents']['sectionListRenderer']
    ['contents'][0]['itemSectionRenderer']
    ['contents']
)

videos = []
novoVideo = []

for dados in conteudo:
    for chave, valor in dados.items():
        if type(valor) is dict:
            for c, v in valor.items():
                if c=="videoId" and len(v)==11:
                    if len(novoVideo) != 0:
                        novoVideo = []
                    novoVideo += [v]
                elif c=="thumbnail" and "thumbnails" in v:
                    if len(novoVideo) != 1:
                        continue

                    novoVideo += [v['thumbnails'][1]['url']]
                elif c=="title" and "runs" in v:
                    if len(novoVideo) != 2:
                        continue

                    novoVideo += [v['runs'][0]['text']]
                    videos += [Video(novoVideo[0], novoVideo[1], novoVideo[2])]


with open("resultados.txt", 'w', encoding="utf-8") as saida:
    for i in range(len(videos)):
        saida.write(videos[i].titulo+'\n')
        saida.write(videos[i].thumbnailUrl+'\n')
        saida.write(videos[i].videoId+'\n')


