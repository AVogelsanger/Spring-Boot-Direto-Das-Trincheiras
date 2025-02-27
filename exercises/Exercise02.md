# Exercise 02 - @RequestParan, @PathVariable

Crie uma classe Anime, dentro de um pacote chamado domain, com os seguintes atributos:
    
     Long id
     String name
Crie também um método dentro de Anime que retorne uma lista "harcoded" de Animes;
Atualize o AnimeController para retornar uma lista de Anime, em seguida,
crie outro dois métodos um para filtrar pelo nome, usando @RequestParam, e o segundo,
para retornar um Anine pelo Id, usando @PathVariable.