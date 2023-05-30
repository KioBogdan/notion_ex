import { useEffect, useRef } from 'react';
import { w3cwebsocket as W3CWebSocket } from 'websocket';

const MyComponent = () => {
  const client = useRef(null);

  useEffect(() => {
    client.current = new W3CWebSocket('ws://localhost:3000/ws');

    client.current.onopen = () => {
      console.log('WebSocket connected');
    };

    client.current.onmessage = (message) => {
      console.log('Received message:', message.data);
      // Handle incoming WebSocket messages
    };

    client.current.onclose = () => {
      console.log('WebSocket closed');
    };

    // return () => {
    //   client.current.close(); // Close the WebSocket connection when the component unmounts
    // };
  }, []);

  const sendMessage = () => {
    if (client.current.readyState === client.current.OPEN) {
      client.current.send('Hello from React');
    }
  };

  return (
    <div>
      {/* Your component's JSX */}
      <button onClick={sendMessage}>Send Message</button>
    </div>
  );
};

export default MyComponent;