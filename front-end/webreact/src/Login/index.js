import React, { useState } from 'react';
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { useLocalState } from '../util/useLocalStorage';
import 'bootstrap/dist/css/bootstrap.min.css';

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const [jwt, setJwt] = useLocalState("", "jwt");
    function sendLogReq() {
        const reqBody = {
             email: username,
             password: password,
        };
          
        fetch("api/login", {
            headers: {
                "Content-Type" : "application/json",
            }, 
            method: "POST",
            body: JSON.stringify(reqBody),
            })
              .then((response) => {
                if(response.status === 200)
                    return Promise.all([response.json(), response.headers]);
                else 
                    return Promise.reject("Invalid login attempt");
              })
              .then(([body, headers]) => {
                setJwt(headers.get("authorization")); //async operation; will not see the result just after it finishes
                window.location.href = "financial";
              })
              .catch((message) => {
                alert(message);
              });
    }

    return (
    <>
        <Container className='mt-5'>
            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='username'>
                    <Form.Label className='fs-5'>Email address</Form.Label>
                    <Form.Control type="email" 
                            value={username} 
                            onChange={(event) => setUsername(event.target.value)} placeholder="Enter email"/>
                </Form.Group>
                </Col>
            </Row>

            <Row className='justify-content-center'>
                <Col md="8" lg="6">
                <Form.Group className="mb-3" controlId='password'>
                    <Form.Label className='fs-5'>Password</Form.Label>
                    <Form.Control type="password" 
                            value={password} 
                            onChange={(event) => setPassword(event.target.value)} placeholder="Type in your password"/>
                </Form.Group>
                </Col>
            </Row>
                {/* <Form.Group className="mb-3" controlId='username'>
                    <Form.Label className='fs-5'>Email address</Form.Label>
                    <Form.Control type="email" 
                            value={username} 
                            onChange={(event) => setUsername(event.target.value)} placeholder="Enter email"/>
                </Form.Group> */}

                {/* <Form.Group className="mb-3" controlId='password'>
                    <Form.Label className='fs-5'>Password</Form.Label>
                    <Form.Control type="password" 
                            value={password} 
                            onChange={(event) => setPassword(event.target.value)} placeholder="Type in your password"/>
                </Form.Group> */}
            {/* </Form> */}

            <Row className='justify-content-center'>
                <Col md="8" lg="6" className='mt-2 d-flex flex-column gap-3 flex-md-row justify-content-between'>
                <Button id="submit" 
                        type="button" 
                        size='lg'
                        onClick={() => sendLogReq()}
                        variant="secondary" >
                    Login
                </Button>

                <Button id="submit" 
                        type="button" 
                        size='lg'
                        onClick={() => {window.location.href = "/"; }}
                        variant="dark" >
                    Exit
                </Button>

                </Col>
            </Row>
        </Container>
    </>
    );
};

export default Login;