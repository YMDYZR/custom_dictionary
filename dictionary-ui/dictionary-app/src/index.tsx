//index.tsx

import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Search from './Search';
import MySidebar from './Sidebar';
import './App.css';
//UXの適合度を計測するための機能。create-react-appで自動的にインストールされる。今回は無視して開発する。
//import reportWebVitals from './reportWebVitals';


//Reactバージョン１８以降は、ReactDOM.renderではなくroot.renderと書くのがいいらしい。
const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <div style={{ position: 'relative' }}>

      {/* 共通して表示するサイドバーはRoutesの外に設置 */}
      <div className='Mysidebar'>
        <MySidebar />
      </div>
      {/* URLによって表示させるコンポーネントを分岐させる。 */}
      <BrowserRouter>
        <div className='verticalSplit'>
          <Routes>
            <Route path="/search" element={<Search />} />
            {/* TODO:カスタム辞書機能のリンク */}
            {/* <Route path="/custom" element={<SearchCustom />} /> */}
          </Routes>
        </div>
      </BrowserRouter>
    </div>
  </React.StrictMode>
);

