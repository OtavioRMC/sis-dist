from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from tinydb import TinyDB, Query

class Item(BaseModel):
    marca: str
    modelo: str
    ano: int
    preco: float

app = FastAPI(
    title= "Veículos API",
    description="API para manipulação de dados de veículos"
)
db = TinyDB("dados.json")

@app.get("/", tags=["Principal"])
def principal():
    return {"mensagem" : "API Veículos"}

@app.post("/veiculos/",tags=["Veículos"])
def adicionar(item: Item):
    """
    Adicionar um novo veículo
    """
    item_dict = item.model_dump()
    item_id = db.insert(item_dict)
    return {"mensagem" : "Veículo adicionado com sucesso!"}

@app.get("/veiculos/", response_model=list[Item],tags=["Veículos"])
def listar():
    """
    Retornar a lista de todos os veículos
    """
    items = db.all()
    return items

@app.get("/veiculos/{item_id}", response_model=Item,tags=["Veículos"])
def retornarPorId(item_id: int):
    """
    Retornar um veículo a partir do ID
    """
    item = db.get(doc_id=item_id)
    if item is None:
        raise HTTPException(status_code=404,detail="Veículo não encontrado!")
    return item

@app.put("/veiculos/", tags=["Veículos"])
def atualizar(item_id: int, item_atualizado: Item):
    """
    Atualizar um veículo existente
    """
    item_dict = item_atualizado.model_dump()
    
    if not db.update(item_dict,doc_ids=[item_id]):
        raise HTTPException(status_code=404,detail="Veículo não encontrado!")

    return {"mensagem" : "Veículo atualizado com sucesso!"}

@app.delete("/veiculos/{item_id}",tags=["Veículos"])
def deletar(item_id: int):
    """
    Apagar um veículo
    """
    item = db.get(doc_id=item_id)
    if item is None:
        raise HTTPException(status_code=404,detail="Veículo não encontrado!")
    db.remove(doc_ids=[item_id])
    return {"mensagem" : "Veículo apagado com sucesso!"}