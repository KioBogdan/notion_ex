import React, { useEffect, useState } from 'react';
import { useLocalState } from '../util/useLocalStorage';
import { Button, Col, Container, Dropdown, Form, Row, Table } from 'react-bootstrap';

const ToDoActivities = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    const [todos, setToDos] = useState(null);
    const [search, setSearch] = useState("");
    const [backToTopButton, setBackToTopButton] = useState(false);
    const [todo, setToDo] = useState({
        taskName: "",
        due: "",
    })

    const scrollUp = () => {
        window.scrollTo({
            top: 0,
            behavior: "smooth",
        })
    }

    useEffect(() => {
        fetch("api/todo", {
            headers: {
                "Content-Type": "application/json",
                Authorization : `Bearer ${jwt}`,
            }, 
            method: "GET",
        })
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((todoData) => {
            setToDos(todoData);
        });

        window.addEventListener("scroll", () => {
            if(window.scrollY > 50)
                setBackToTopButton(true);
            else 
                setBackToTopButton(false);
        })

    }, [])

    function createToDo() {
        fetch("api/todo", {
        headers: {
            "Content-Type": "application/json",
            Authorization : `Bearer ${jwt}`,
        },
        method: "PUT", 
        body: JSON.stringify(todo),
    }) 
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((data) => {
            console.log(data);
        })
    }

    function fetchSorted(param) {
        fetch(`api/todo/sort/${param}`, {
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${jwt}`,
            },
            method: "GET",
        })
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((data) => {
            setToDos(data);
            console.log(data);
        })
    }

    function fetchSearch(param) {
        fetch(`api/todo/search/${param}`, {
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${jwt}`,
            },
            method: "GET",
        })
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((data) => {
            setToDos(data);
            console.log(data);
        })
    }

    function updateToDo(prop, value) {
        const newToDo = { ...todo };
        newToDo[prop] = value;
        setToDo(newToDo);
    }

    return (
      <Container>
        <Table striped bordered hover variant="dark">
            <thead>
                <tr>
                <th>#</th>
                    <th>Task Name</th>
                    <th>Due</th>
                </tr>
            </thead>
          <tbody>
            {todos ? todos.map((todo) => (
                    <tr>
                        <td>{todo.id}</td>
                        <td>{todo.taskName}</td>
                        <td>{todo.due}</td>
                    </tr> )) : <></>
            }
          </tbody>
        </Table>
          
        <Container className='mt-5'>
            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='taskName'>
                    <Form.Label className='fs-5'>Task Name</Form.Label>
                    <Form.Control type="text"  
                            onChange={(e) => updateToDo("taskName", e.target.value)} 
                            value={todo.taskName}
                            placeholder="Enter task name"/>
                </Form.Group>
                </Col>
            </Row>

            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='due'>
                    <Form.Label className='fs-5'>Due</Form.Label>
                    <Form.Control type="date" 
                            onChange={(e) => updateToDo("due", e.target.value)} 
                            value={todo.due}
                            placeholder="Enter due date"/>
                </Form.Group>
                </Col>
            </Row>
        </Container>
        
        <Dropdown>
                <Dropdown.Toggle variant="success" id="dropdown-basic">
                    Sort by:
                </Dropdown.Toggle>

                <Dropdown.Menu>
                    <Dropdown.Item  onClick={() => fetchSorted("taskName")} >Task Name</Dropdown.Item>
                    <Dropdown.Item  onClick={() => fetchSorted("due")}>Due</Dropdown.Item>
                </Dropdown.Menu>
        </Dropdown>
        
        <button onClick={() => createToDo()}> Add a To Do task </button>
        {backToTopButton && (
            <button style={
                {
                    position: "fixed",
                    bottom: "50px",
                    right: "50px",
                    height: "50px",
                    width: "50px",
                    fontSize: "50px",
                }}
                onClick={scrollUp}
            >

            </button>
          )
          }
          <Container>
            <Row className='justify-content-center'>
                <Col md="8" lg="6" className='mt-2 d-flex flex-column gap-3 flex-md-row justify-content-between'>
                <Form.Group className="mb-3" controlId='search'>
                    <Form.Label className='fs-5'>Search</Form.Label>
                    <Form.Control type="text" 
                            value={search} 
                            onChange={(event) => setSearch(event.target.value)} 
                            placeholder="search for a task"/>
                </Form.Group>

                <Button id="submit" 
                        type="button" 
                        size='lg'
                        onClick={() => fetchSearch(search)}
                        variant="dark" >
                    Search for
                </Button>

                </Col>
            </Row>
        </Container>
        </Container>
      );

    // return (
    //     <div style={{ margin: "2em" }}>
    //         {todos ? todos.map((todo) => (
    //             <div>To Do Activity ID: {todo.id}</div>)) : <></>}
    //         <button onClick={() => createToDo()}> Add a To do task </button>
    //     </div>
    // );
};

export default ToDoActivities;