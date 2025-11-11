# Aula Sistemas Distribuidos 
from fastapi import FastAPI
from pydantic import BaseModel


app = FastAPI(
    title="API com FastAPI",
    description="API de exemplo para demonstrar o uso do FastAPI",
    version="1.0.0",
    contact={
        "name": "João Silva",
        "email": "joao.silva@email.com",
    },
    license_info={
        "name": "MIT License",
        "url": "https://opensource.org/MIT",
    },
)


@app.get("/")
def root():
    """
    Retorna uma mensagem de boas-vindas
    - **Sem parâmetros
    - **Retorno:** mensagem em formato de json
    """
    return {"mensagem": "Hello, FastAPI!"}


@app.get("/hello/{nome}", tags=["Principal"])
def hello_nome(nome: str):
    return {"mensagem": f"Hello, {nome}!"}


class Usuario(BaseModel):
    nome: str
    idade: int
    email: str


@app.post("/usuarios/")
def criar_usuario(usuario: Usuario):
    return {"mensagem": f"Usuário {usuario.nome} criado com sucesso!", 
           "dados": usuario
    }

@app.get("/soma/")
def somar(a: int, b:int ):
    return {"resultado": a + b}
    