import React from 'react';

//データの型
type Props = {
    response: { termName: string, summary: string, description: string, tagName: string[] }[] | null;
    setResponse: React.Dispatch<React.SetStateAction<{
        termName: string;
        summary: string;
        description: string;
        tagName: string[];
    }[] | null>>;
}

//受け取ったresponseを画面に表示するためのコンポーネント
const SearchResults = ({ response, setResponse }: Props) => {
    //responseを表示するテーブル
    let tableBody;
    if (response != null) {
        tableBody = response.map((row) => {
            return (
                <tr>
                    <td>{row.termName}</td>
                    <td>{row.summary}</td>
                    <td>{row.description}</td>
                    <td>{row.tagName.join('\n・')}</td>
                </tr>
            );
        })
    } else {
        tableBody = <tr aria-colspan={4}><td>該当データがありません。</td></tr>
    }

    return (
        <table>
            <thead>
                <tr>
                    <th>用語名</th>
                    <th>要約</th>
                    <th>詳細</th>
                    <th>タグ</th>
                </tr>
            </thead>
            <tbody>
                {tableBody}
            </tbody>
        </table>
    );
}

export default SearchResults;