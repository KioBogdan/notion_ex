import React, { useEffect, useState } from 'react';
import { useLocalState } from '../util/useLocalStorage';
import { Button, Col, Container, Dropdown, Form, Row, Table } from 'react-bootstrap';
import { EmailIcon, EmailShareButton, FacebookIcon, FacebookShareButton, RedditIcon, RedditShareButton, TwitterIcon, TwitterShareButton } from 'react-share';

const ToDoActivities = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    const [todos, setToDos] = useState(null);
    const [search, setSearch] = useState("");
    const [backToTopButton, setBackToTopButton] = useState(false);
    const shareUrl = 'https://localhost:3000'; // URL to be shared
    const title = 'Still developping this site, but check it out!'; // Title of the shared content
    const [todo, setToDo] = useState({
        taskName: "",
        due: "",
    })

    const [file, setFile] = useState(); // setting file to import
    const [array, setArray] = useState([]);

    const fileReader = new FileReader();

    const handleOnChange = (e) => {
        setFile(e.target.files[0]);
    };

    const csvFileToArray = string => {
        const csvHeader = string.slice(0, string.indexOf("\n")).split(";");
        const csvRows = string.slice(string.indexOf("\r")+1).split("\r");

        const todoArray = csvRows.map(i => {
            const values = i.split(";");
            const obj = csvHeader.reduce((object, header, index) => {
                object[header] = values[index];
                return object;
            }, {});

            return obj;
        });

        // const financialArray = csvRows.map(i => {
        //     const values = i.split(";");

        //     const financialObj = {
        //         expense: values[1],
        //         amount: values[2],
        //         category: values[3],
        //         date: values[4],
        //     };

        //     console.log(financialObj);
        //     createFinancialParam(financialObj);
        //     return financialObj;

        // });
            
        console.log(todoArray);
        
        for(let i=0; i<= todoArray.length-2; i++) {
                // console.log(financialArray[i].expense);
                // updateFinancial("expense", financialArray[i].expense);
                // updateFinancial("amount", financialArray[i].amount);
                // updateFinancial("category", financialArray[i].category);
                // updateFinancial("date", financialArray[i].date);

                createToDoParam(todoArray[i]);
        }
        //         console.log(financialArray[i]);
        //         // financial.expense = financialArray[i].expense;
        //         // financial.amount = financialArray[i].amount;
        //         // financial.category = financialArray[i].category;
        //         // financial.date = financialArray[i].date;
        //         //setFinancial(financialArray[i]); //= financialArray[i];
        //         //console.log(financialItem);
        //         //setFinancial(financialArray[i]);
        //         createFinancialParam(financial);
    };

    const handleOnSubmit = (e) => {
        e.preventDefault();

        if(file) {
            fileReader.onload = function (event) {
                const csvOutput = event.target.result;
                csvFileToArray(csvOutput);
            };

            fileReader.readAsText(file);

            //console.log(fileReader);
        }
    };

    const headerKeys = Object.keys(Object.assign({}, ...array));

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

    function createToDoParam(param) {
        fetch("api/todo", {
        headers: {
            "Content-Type": "application/json",
            Authorization : `Bearer ${jwt}`,
        },
        method: "PUT", 
        body: JSON.stringify(param),
    }) 
        .then((response) => {
            if(response.status === 200) return response.json();
        })
        .then((data) => {
            setToDo(data);
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

                <Col md="8" lg="6">
                    <Form.Group className="mb-3" controlId='due'>
                        <Form.Label className='fs-5'>Due</Form.Label>
                        <Form.Control type="date" 
                                onChange={(e) => updateToDo("due", e.target.value)} 
                                value={todo.due}
                                placeholder="Enter due date"/>
                    </Form.Group>
                </Col>

                <Col>
                        <Button className='justify-content-center' onClick={() => createToDo()}> Add a To Do task </Button>
                </Col>

                {/* <Col>
                    <Button className='justify-content-center' onClick={() => createToDo()}> Add a To Do task </Button>
                </Col> */}
            </Row>

            <Row>
                        <Dropdown className='justify-content-center'>
                            <Dropdown.Toggle variant="success" id="dropdown-basic">
                                Sort by:
                            </Dropdown.Toggle>

                            <Dropdown.Menu>
                                <Dropdown.Item  onClick={() => fetchSorted("taskName")} >Task Name</Dropdown.Item>
                                <Dropdown.Item  onClick={() => fetchSorted("due")}>Due</Dropdown.Item>
                            </Dropdown.Menu>
                        </Dropdown>
            </Row>
        </Container>

            {/* <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='due'>
                    <Form.Label className='fs-5'>Due</Form.Label>
                    <Form.Control type="date" 
                            onChange={(e) => updateToDo("due", e.target.value)} 
                            value={todo.due}
                            placeholder="Enter due date"/>
                </Form.Group>
                </Col>
            </Row> */}
        
        {/* <Container>
            <Row className='justify-content-center'>
                <Col md="8" lg="6" className='mt-2 d-flex flex-column gap-3 flex-md-row justify-content-between'>
                    <Dropdown className='justify-content-center'>
                        <Dropdown.Toggle variant="success" id="dropdown-basic">
                            Sort by:
                        </Dropdown.Toggle>

                        <Dropdown.Menu>
                            <Dropdown.Item  onClick={() => fetchSorted("taskName")} >Task Name</Dropdown.Item>
                            <Dropdown.Item  onClick={() => fetchSorted("due")}>Due</Dropdown.Item>
                        </Dropdown.Menu>
                    </Dropdown>

                    <Button className='justify-content-center' onClick={() => createToDo()}> Add a To Do task </Button>
                </Col>
            </Row>
        </Container> */}
                {/* <Col md="8" lg="6">
                    <Button onClick={() => createToDo()}> Add a To Do task </Button>
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
                    )}
                </Col> */}
        {/* <Dropdown>
                <Dropdown.Toggle variant="success" id="dropdown-basic">
                    Sort by:
                </Dropdown.Toggle>

                <Dropdown.Menu>
                    <Dropdown.Item  onClick={() => fetchSorted("taskName")} >Task Name</Dropdown.Item>
                    <Dropdown.Item  onClick={() => fetchSorted("due")}>Due</Dropdown.Item>
                </Dropdown.Menu>
        </Dropdown> */}
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
                    <Col md="8" lg="6">
                        <Form className="mb-3" controlId='expense'>
                            <input 
                                type={"file"}
                                id={"csvFileInput"}
                                accept={".csv"}
                                onChange={handleOnChange}
                            />

                            <button 
                                onClick={(e) => {
                                    handleOnSubmit(e);
                                }}
                            >
                                IMPORT CSV 
                            </button>
                        </Form>
                    </Col>
                </Row>
        </Container>

          <Container>
            <Row className='justify-content-center'>
                <Col md="12" lg="6" className='mt-2 d-flex flex-column gap-3 flex-md-row justify-content-between'>
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

            <Row className='justify-content-center'>
                <Col md="8" lg="6" className='mt-2 d-flex gap-3 flex-md-row justify-content-between'>
                    <FacebookShareButton url={shareUrl} quote={title}>
                        <FacebookIcon logoFillColor="blue" />
                    </FacebookShareButton>

                    <TwitterShareButton url={shareUrl} title={title}>
                        <TwitterIcon logoFillColor="green" />
                    </TwitterShareButton>

                    <EmailShareButton url={shareUrl} title={title}>
                        <EmailIcon logoFillColor="mauve" />
                    </EmailShareButton>

                    <RedditShareButton url={shareUrl} title={title}>
                        <RedditIcon logoFillColor="orange" />
                    </RedditShareButton>
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